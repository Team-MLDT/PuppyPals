package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.annotations.HasMany;

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

/** This is an auto generated class representing the Event type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Events")
public final class Event implements Model {
  public static final QueryField ID = field("Event", "id");
  public static final QueryField EVENT_DESCRIPTION = field("Event", "eventDescription");
  public static final QueryField LAT = field("Event", "lat");
  public static final QueryField LON = field("Event", "lon");
  public static final QueryField EVENT_DATE = field("Event", "eventDate");
  public static final QueryField EVENT_IMAGE_URL = field("Event", "eventImageURL");
  public static final QueryField HOST = field("Event", "userEventsHostedId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String eventDescription;
  private final @ModelField(targetType="String", isRequired = true) String lat;
  private final @ModelField(targetType="String", isRequired = true) String lon;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime eventDate;
  private final @ModelField(targetType="String") String eventImageURL;
  private final @ModelField(targetType="User") @BelongsTo(targetName = "userEventsHostedId", type = User.class) User host;
  private final @ModelField(targetType="DogEvents") @HasMany(associatedWith = "event", type = DogEvents.class) List<DogEvents> dogs = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getEventDescription() {
      return eventDescription;
  }
  
  public String getLat() {
      return lat;
  }
  
  public String getLon() {
      return lon;
  }
  
  public Temporal.DateTime getEventDate() {
      return eventDate;
  }
  
  public String getEventImageUrl() {
      return eventImageURL;
  }
  
  public User getHost() {
      return host;
  }
  
  public List<DogEvents> getDogs() {
      return dogs;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Event(String id, String eventDescription, String lat, String lon, Temporal.DateTime eventDate, String eventImageURL, User host) {
    this.id = id;
    this.eventDescription = eventDescription;
    this.lat = lat;
    this.lon = lon;
    this.eventDate = eventDate;
    this.eventImageURL = eventImageURL;
    this.host = host;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Event event = (Event) obj;
      return ObjectsCompat.equals(getId(), event.getId()) &&
              ObjectsCompat.equals(getEventDescription(), event.getEventDescription()) &&
              ObjectsCompat.equals(getLat(), event.getLat()) &&
              ObjectsCompat.equals(getLon(), event.getLon()) &&
              ObjectsCompat.equals(getEventDate(), event.getEventDate()) &&
              ObjectsCompat.equals(getEventImageUrl(), event.getEventImageUrl()) &&
              ObjectsCompat.equals(getHost(), event.getHost()) &&
              ObjectsCompat.equals(getCreatedAt(), event.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), event.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getEventDescription())
      .append(getLat())
      .append(getLon())
      .append(getEventDate())
      .append(getEventImageUrl())
      .append(getHost())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Event {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("eventDescription=" + String.valueOf(getEventDescription()) + ", ")
      .append("lat=" + String.valueOf(getLat()) + ", ")
      .append("lon=" + String.valueOf(getLon()) + ", ")
      .append("eventDate=" + String.valueOf(getEventDate()) + ", ")
      .append("eventImageURL=" + String.valueOf(getEventImageUrl()) + ", ")
      .append("host=" + String.valueOf(getHost()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static EventDescriptionStep builder() {
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
  public static Event justId(String id) {
    return new Event(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      eventDescription,
      lat,
      lon,
      eventDate,
      eventImageURL,
      host);
  }
  public interface EventDescriptionStep {
    LatStep eventDescription(String eventDescription);
  }
  

  public interface LatStep {
    LonStep lat(String lat);
  }
  

  public interface LonStep {
    EventDateStep lon(String lon);
  }
  

  public interface EventDateStep {
    BuildStep eventDate(Temporal.DateTime eventDate);
  }
  

  public interface BuildStep {
    Event build();
    BuildStep id(String id);
    BuildStep eventImageUrl(String eventImageUrl);
    BuildStep host(User host);
  }
  

  public static class Builder implements EventDescriptionStep, LatStep, LonStep, EventDateStep, BuildStep {
    private String id;
    private String eventDescription;
    private String lat;
    private String lon;
    private Temporal.DateTime eventDate;
    private String eventImageURL;
    private User host;
    @Override
     public Event build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Event(
          id,
          eventDescription,
          lat,
          lon,
          eventDate,
          eventImageURL,
          host);
    }
    
    @Override
     public LatStep eventDescription(String eventDescription) {
        Objects.requireNonNull(eventDescription);
        this.eventDescription = eventDescription;
        return this;
    }
    
    @Override
     public LonStep lat(String lat) {
        Objects.requireNonNull(lat);
        this.lat = lat;
        return this;
    }
    
    @Override
     public EventDateStep lon(String lon) {
        Objects.requireNonNull(lon);
        this.lon = lon;
        return this;
    }
    
    @Override
     public BuildStep eventDate(Temporal.DateTime eventDate) {
        Objects.requireNonNull(eventDate);
        this.eventDate = eventDate;
        return this;
    }
    
    @Override
     public BuildStep eventImageUrl(String eventImageUrl) {
        this.eventImageURL = eventImageUrl;
        return this;
    }
    
    @Override
     public BuildStep host(User host) {
        this.host = host;
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
    private CopyOfBuilder(String id, String eventDescription, String lat, String lon, Temporal.DateTime eventDate, String eventImageUrl, User host) {
      super.id(id);
      super.eventDescription(eventDescription)
        .lat(lat)
        .lon(lon)
        .eventDate(eventDate)
        .eventImageUrl(eventImageUrl)
        .host(host);
    }
    
    @Override
     public CopyOfBuilder eventDescription(String eventDescription) {
      return (CopyOfBuilder) super.eventDescription(eventDescription);
    }
    
    @Override
     public CopyOfBuilder lat(String lat) {
      return (CopyOfBuilder) super.lat(lat);
    }
    
    @Override
     public CopyOfBuilder lon(String lon) {
      return (CopyOfBuilder) super.lon(lon);
    }
    
    @Override
     public CopyOfBuilder eventDate(Temporal.DateTime eventDate) {
      return (CopyOfBuilder) super.eventDate(eventDate);
    }
    
    @Override
     public CopyOfBuilder eventImageUrl(String eventImageUrl) {
      return (CopyOfBuilder) super.eventImageUrl(eventImageUrl);
    }
    
    @Override
     public CopyOfBuilder host(User host) {
      return (CopyOfBuilder) super.host(host);
    }
  }
  
}
