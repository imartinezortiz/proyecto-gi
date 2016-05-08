package ucm.fdi.tfg.events;

public interface Adaptable {
  <T> T getAdapter(Class<T> clazz);
}
