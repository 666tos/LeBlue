package tests

import houtbecke.rs.le.*
import houtbecke.rs.le.mock.LeDeviceMock
import houtbecke.rs.le.mock.LeSessionController
import houtbecke.rs.le.session.EventSinkFiller
import houtbecke.rs.le.session.ListEventSinkSource
import houtbecke.rs.le.session.SessionObject
import org.junit.Before
import org.junit.Test

import static houtbecke.rs.le.session.EventType.*

class MockerTest {

    LeSessionController sessionController
    LeDevice device

    final int LE_REMOTE_DEVICE = 11
    final int LE_REMOTE_DEVICE_2 = 12

    final int LE_SERVICE_1_1 = 101
    final int LE_SERVICE_1_2 = 102
    final int LE_SERVICE_2_1 = 201

    final int LE_CHARACTERISTIC_1_1 = 1001
    final int LE_CHARACTERISTIC_1_2 = 1002
    final int LE_CHARACTERISTIC_2_1 = 2001


    @Before
    void setUp() throws Exception {
        System.setProperty "doNotLog", "true"
    }

    def createSource() {
        def source = new ListEventSinkSource();
        def filler = new EventSinkFiller(source)

        filler.addDeviceEvent(deviceStartScanning);

        filler.addDeviceEvent(mockRemoteDeviceFound,
                    LE_REMOTE_DEVICE,
                    "123",
                    "0,1,2")

        // the test will tell us to wait for the ready signal before going on
        filler.waitForPoint("ready")

        filler.addEvent(mockCharacteristicChanged, LE_REMOTE_DEVICE, LE_CHARACTERISTIC_1_2)


        filler.addDeviceEvent(mockRemoteDeviceFound,
                LE_REMOTE_DEVICE_2,
                "123",
                "0,1,2")


        filler.pointReached("secondDevice")


        filler.waitForPoint("done")

        return source
    }

    LeRemoteDevice remoteDevice

    @Test
    void testController() {
        def events = createSource();

        sessionController = new LeSessionController(

             SessionObject.newSession()
                 .withDefaultSessionSource(events)

                .withDeviceMocker()
                    .withFakeDeviceListeners()

                .and.withRemoteDeviceMocker(
                    LE_REMOTE_DEVICE)
                    .mocksRemoteDevice("0001:0002:0003:0004", "d1234", true)
                    .withFakeRemoteDeviceListeners()
                    .withFakeCharacteristicsListeners()
                    .hasServices(LeGattStatus.SUCCESS, LE_SERVICE_1_1, LE_SERVICE_1_2)

                 .and.withRemoteDeviceMocker(
                     LE_REMOTE_DEVICE_2)
                     .mocksRemoteDevice("0005:0006:0007:0008", "d5678", true)
                     .withFakeRemoteDeviceListeners()
                     .withFakeCharacteristicsListeners()
                     .hasServices(LeGattStatus.SUCCESS, LE_SERVICE_2_1)


                     .and.withGattServiceMocker(LE_SERVICE_1_1)
                    .mocksService(UUID.fromString("12345678-1234-1234-1234-123456789aaaa"))
                    .hasCharacteristic(LE_CHARACTERISTIC_1_1, UUID.fromString("12345678-1234-1234-1234-123456789bbbb"))

                .and.withGattCharacteristicsMocker(
                    LE_CHARACTERISTIC_1_1)
                    .mocksCharacteristic()
                    .hasFixedValue(0, 1, 2)

                .and.withGattCharacteristicsMocker(
                     LE_CHARACTERISTIC_1_2)
                     .mocksCharacteristic("12345678-1234-1234-1234-123456789bbcc")
                     .hasFixedValue(0, 1, 2)


                .and.withGattCharacteristicsMocker(
                     LE_CHARACTERISTIC_2_1)
                     .mocksCharacteristic(UUID.fromString("12345678-1234-1234-1234-123456789eeee"))
                     .hasValue(0, 1, 2)
                     .hasValue(3, 4, 5)
                     .hasFixedValue(6, 7, 8)

                .and.withGattServiceMocker(LE_SERVICE_2_1)
                    .mocksService(UUID.fromString("12345678-1234-1234-1234-123456789dddd"))
                    .hasCharacteristic(LE_CHARACTERISTIC_2_1)


                .end()
        )




        device = new LeDeviceMock(sessionController)
        sessionController.startDefaultSession()
        assert sessionController.waitTillSessionStarted()
        def foundRemoteDevice = false, foundRemoteDevice2 = false

        device.addListener(new LeDeviceListener() {
            @Override
            void leDeviceFound(LeDevice leDeviceFound, LeRemoteDevice leFoundRemoteDevice, int rssi, byte[] scanRecord) {
                assert device == leDeviceFound
                assert leFoundRemoteDevice != null
                assert rssi == 123
                assert scanRecord == [0, 1, 2]

                remoteDevice = leFoundRemoteDevice

                foundRemoteDevice = true
            }
        })

        device.addListener(new LeDeviceListener() {
            @Override
            void leDeviceFound(LeDevice leDeviceFound, LeRemoteDevice leFoundRemoteDevice, int rssi, byte[] scanRecord) {
                foundRemoteDevice2 = true
            }
        })

        device.startScanning()
        Thread.sleep(100)
        assert foundRemoteDevice && foundRemoteDevice2, "check both listeners are notified"

        assert remoteDevice.getAddress() == "0001:0002:0003:0004"
        assert remoteDevice.getName() == "d1234"

        def connected = false, disconnected = false, closed = false, discovered = false;

        LeGattService service;
        remoteDevice.addListener(new LeRemoteDeviceListener() {

            @Override
            void leDevicesConnected(LeDevice leDeviceFoundOn, LeRemoteDevice leRemoteDevice) {
                assert device == leDeviceFoundOn
                assert remoteDevice == leRemoteDevice
                connected = true;
            }

            @Override
            void leDevicesDisconnected(LeDevice leDevice, LeRemoteDevice leRemoteDevice) {

            }

            @Override
            void leDevicesClosed(LeDevice leDevice, LeRemoteDevice leRemoteDevice) {

            }

            @Override
            void serviceDiscovered(LeDevice leDevice, LeRemoteDevice leRemoteDevice, LeGattStatus status, LeGattService[] gatts) {
                discovered = true;
                assert device == leDevice;
                assert leRemoteDevice == remoteDevice
                assert LeGattStatus.SUCCESS == status;
                assert gatts.length == 2
                service = gatts[0]
            }
        })

        remoteDevice.connect();

        Thread.sleep(100);
        assert connected

        remoteDevice.startServicesDiscovery()

        Thread.sleep(1000);
        assert discovered

        service.getUuid() == UUID.fromString("12345678-1234-1234-1234-123456789aaaa")

        def characteristic = service.getCharacteristic(UUID.fromString("12345678-1234-1234-1234-123456789bbbb"))
        assert characteristic != null
        assert characteristic.getValue() == [0, 1, 2]
        assert characteristic.getValue() == [0, 1, 2]

        def changed = false

        remoteDevice.setCharacteristicListener(new LeCharacteristicListener() {
            @Override
            void leCharacteristicChanged(UUID uuid, LeRemoteDevice leRemoteDevice, LeGattCharacteristic leCharacteristic) {
                assert uuid == UUID.fromString("12345678-1234-1234-1234-123456789bbcc")
                assert remoteDevice == leRemoteDevice
                assert leCharacteristic != characteristic, "make sure this is a different characteristic"
                changed = true;
            }
        }, UUID.fromString("12345678-1234-1234-1234-123456789cccc"))

        service.enableCharacteristicNotification(UUID.fromString("12345678-1234-1234-1234-123456789cccc"))

        // signal to the script that we are at the point where our listeners etc are working.
        sessionController.pointReached("ready")


        Thread.sleep(100);
        assert changed;

        characteristic.setValue([3, 4, 5] as byte[]);


        sessionController.waitForPoint("secondDevice")

        assert remoteDevice.getAddress() ==  "0005:0006:0007:0008"

        remoteDevice.addListener(new LeRemoteDeviceListener() {
            @Override
            void leDevicesConnected(LeDevice leDevice, LeRemoteDevice leRemoteDevice) {

            }

            @Override
            void leDevicesDisconnected(LeDevice leDevice, LeRemoteDevice leRemoteDevice) {

            }

            @Override
            void leDevicesClosed(LeDevice leDevice, LeRemoteDevice leRemoteDevice) {

            }

            @Override
            void serviceDiscovered(LeDevice leDevice, LeRemoteDevice leRemoteDevice, LeGattStatus status, LeGattService[] gatts) {
                service = gatts[0]
            }
        })
        remoteDevice.startServicesDiscovery()
        Thread.sleep(100)

        def char21 = service.getCharacteristic(UUID.fromString("12345678-1234-1234-1234-123456789eeee"))

        changed = false;

        assert char21.getValue() == [0, 1, 2]
        assert char21.getValue() == [3, 4, 5]
        assert char21.getValue() == [6, 7, 8]
        assert char21.getValue() == [6, 7, 8]

        sessionController.pointReached("done")

        sessionController.waitForFinishedSession()

        assert !events.hasMoreEvent()
        assert sessionController.getSessionException() == null
    }
}