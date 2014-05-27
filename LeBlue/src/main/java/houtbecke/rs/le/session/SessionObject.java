package houtbecke.rs.le.session;

import java.util.HashMap;
import java.util.Map;

public class SessionObject implements Session {

    public static SessionObject newSession() {
        return new SessionObject();
    }

    public MockerObject withObjectMocker(int source) {
        return MockerObject.newMocker(this, source);
    }

    int defaultDelay = 0;

    public SessionObject setDefaultDelay(int defaultDelay) {
        this.defaultDelay=defaultDelay;
        return this;
    }

    public int getDefaultDelay() {
        return defaultDelay;
    }

    public SessionObject setDefaultSource(EventSource defaultSource) {
        this.defaultSource = defaultSource;
        return this;
    }

    public DeviceMockerObject withDeviceMocker() {
        return withDeviceMocker(EventSinkFiller.DEFAULT_DEVICE_ID);
    }

    public DeviceMockerObject withDeviceMocker(int id) {
        DeviceMockerObject ret = new DeviceMockerObject(this, id);
        devices.put(id, ret);
        return ret;
    }

    public SessionObject withDeviceMocker(int id, Mocker device) {
        devices.put(id, device);
        return this;
    }

    public SessionObject withDeviceMocker(Mocker device) {
        devices.put(EventSinkFiller.DEFAULT_DEVICE_ID, device);
        return this;
    }

    public RemoteDeviceMockerObject withRemoteDeviceMocker(int id) {
        RemoteDeviceMockerObject remoteDeviceMocker = new RemoteDeviceMockerObject(this, id);
        remoteDevices.put(id, remoteDeviceMocker);
        return remoteDeviceMocker;
    }

    public SessionObject withRemoteDeviceMocker(int id, Mocker remoteDevice) {
        remoteDevices.put(id, remoteDevice);
        return this;
    }

    public GattServiceMockerObject withGattServiceMocker(int id) {
        GattServiceMockerObject service = new GattServiceMockerObject(this, id);
        gattServices.put(id, service);
        return service;
    }

    public SessionObject withGattServiceMocker(int id, Mocker service) {
        gattServices.put(id, service);
        return this;
    }

    public CharacteristicsMockerObject withGattCharacteristicsMocker(int id) {
        CharacteristicsMockerObject ret = new CharacteristicsMockerObject(this, id);
        gattCharacteristics.put(id, ret);
        return ret;
    }

    public SessionObject withGattCharacteristicsMocker(int id, Mocker characteristic) {
        gattCharacteristics.put(id, characteristic);
        return this;
    }

    public SessionObject withNamedEventSource(String name, EventSource eventSource) {
        eventSources.put(name, eventSource);
        return this;
    }

    public SessionObject withDefaultSessionSource(EventSource defaultSource) {
        this.defaultSource = defaultSource;
        return this;
    }

    Map<Integer, Mocker> devices = new HashMap<>();
    Map<Integer, Mocker> remoteDevices = new HashMap<>();
    Map<Integer, Mocker> gattServices = new HashMap<>();
    Map<Integer, Mocker> gattCharacteristics = new HashMap<>();

    EventSource defaultSource;

    Map<String, EventSource> eventSources = new HashMap<>(0);

    @Override
    public Mocker getGattCharacteristicMocker(int id) {
        return gattCharacteristics.get(id);
    }

    @Override
    public Mocker getGattServiceMocker(int id) {
        return gattServices.get(id);
    }

    @Override
    public Mocker getDeviceMocker(int id) {
        return devices.get(id);
    }

    @Override
    public Mocker getRemoteDeviceMocker(int device) {
        return remoteDevices.get(device);
    }

    @Override
    public EventSource getDefaultSource() {
        return defaultSource;
    }

    @Override
    public EventSource getNamedEventSource(String source) {
        return eventSources.get(source);
    }


    Map<Integer, String> sourceIdentifications = new HashMap<>();

    @Override
    public String getSourceIdentification(int source) {
        String ret = sourceIdentifications.get(source);
        if (ret == null) throw new RuntimeException("Trying to reference a mocked source by only it's id,  and it's identification string has not yet been specified");
        return ret;
    }

    @Override
    public void setSourceIdentification(int source, String identification) {
        sourceIdentifications.put(source, identification);
    }
}