package eu.scislo.mobilenext;

public class RasterGraphicsEventPayload<T> extends RasterGraphicsEvent {
    public T payload;

    public RasterGraphicsEventPayload(RasterGraphicsEventTypes type, T payload) {
        super(type);
        this.payload = payload;
    }
}
