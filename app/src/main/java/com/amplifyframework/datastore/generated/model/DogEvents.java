package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the DogEvents type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "DogEvents")
@Index(name = "byDog", fields = {"dogID"})
@Index(name = "byEvent", fields = {"eventID"})
public final class DogEvents implements Model {
  public static final QueryField ID = field("DogEvents", "id");
  public static final QueryField DOG = field("DogEvents", "dogID");
  public static final QueryField EVENT = field("DogEvents", "eventID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Dog", isRequired = true) @BelongsTo(targetName = "dogID", type = Dog.class) Dog dog;
  private final @ModelField(targetType="Event", isRequired = true) @BelongsTo(targetName = "eventID", type = Event.class) Event event;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public Dog getDog() {
      return dog;
  }
  
  public Event getEvent() {
      return event;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private DogEvents(String id, Dog dog, Event event) {
    this.id = id;
    this.dog = dog;
    this.event = event;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      DogEvents dogEvents = (DogEvents) obj;
      return ObjectsCompat.equals(getId(), dogEvents.getId()) &&
              ObjectsCompat.equals(getDog(), dogEvents.getDog()) &&
              ObjectsCompat.equals(getEvent(), dogEvents.getEvent()) &&
              ObjectsCompat.equals(getCreatedAt(), dogEvents.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), dogEvents.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDog())
      .append(getEvent())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("DogEvents {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("dog=" + String.valueOf(getDog()) + ", ")
      .append("event=" + String.valueOf(getEvent()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DogStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static DogEvents justId(String id) {
    return new DogEvents(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      dog,
      event);
  }
  public interface DogStep {
    EventStep dog(Dog dog);
  }
  

  public interface EventStep {
    BuildStep event(Event event);
  }
  

  public interface BuildStep {
    DogEvents build();
    BuildStep id(String id);
  }
  

  public static class Builder implements DogStep, EventStep, BuildStep {
    private String id;
    private Dog dog;
    private Event event;
    @Override
     public DogEvents build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new DogEvents(
          id,
          dog,
          event);
    }
    
    @Override
     public EventStep dog(Dog dog) {
        Objects.requireNonNull(dog);
        this.dog = dog;
        return this;
    }
    
    @Override
     public BuildStep event(Event event) {
        Objects.requireNonNull(event);
        this.event = event;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Dog dog, Event event) {
      super.id(id);
      super.dog(dog)
        .event(event);
    }
    
    @Override
     public CopyOfBuilder dog(Dog dog) {
      return (CopyOfBuilder) super.dog(dog);
    }
    
    @Override
     public CopyOfBuilder event(Event event) {
      return (CopyOfBuilder) super.event(event);
    }
  }
  
}
