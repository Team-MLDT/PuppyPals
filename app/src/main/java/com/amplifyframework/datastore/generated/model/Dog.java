package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Dog type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Dogs", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Dog implements Model {
  public static final QueryField ID = field("Dog", "id");
  public static final QueryField DOG_NAME = field("Dog", "dogName");
  public static final QueryField DOG_BREED = field("Dog", "dogBreed");
  public static final QueryField DOG_BIO = field("Dog", "dogBio");
  public static final QueryField PROFILE_IMAGE_URL = field("Dog", "profileImageURL");
  public static final QueryField OWNER = field("Dog", "userDogsId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String dogName;
  private final @ModelField(targetType="String") String dogBreed;
  private final @ModelField(targetType="String") String dogBio;
  private final @ModelField(targetType="String") String profileImageURL;
  private final @ModelField(targetType="User") @BelongsTo(targetName = "userDogsId", type = User.class) User owner;
  private final @ModelField(targetType="DogEvents") @HasMany(associatedWith = "dog", type = DogEvents.class) List<DogEvents> events = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getDogName() {
      return dogName;
  }
  
  public String getDogBreed() {
      return dogBreed;
  }
  
  public String getDogBio() {
      return dogBio;
  }
  
  public String getProfileImageUrl() {
      return profileImageURL;
  }
  
  public User getOwner() {
      return owner;
  }
  
  public List<DogEvents> getEvents() {
      return events;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Dog(String id, String dogName, String dogBreed, String dogBio, String profileImageURL, User owner) {
    this.id = id;
    this.dogName = dogName;
    this.dogBreed = dogBreed;
    this.dogBio = dogBio;
    this.profileImageURL = profileImageURL;
    this.owner = owner;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Dog dog = (Dog) obj;
      return ObjectsCompat.equals(getId(), dog.getId()) &&
              ObjectsCompat.equals(getDogName(), dog.getDogName()) &&
              ObjectsCompat.equals(getDogBreed(), dog.getDogBreed()) &&
              ObjectsCompat.equals(getDogBio(), dog.getDogBio()) &&
              ObjectsCompat.equals(getProfileImageUrl(), dog.getProfileImageUrl()) &&
              ObjectsCompat.equals(getOwner(), dog.getOwner()) &&
              ObjectsCompat.equals(getCreatedAt(), dog.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), dog.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDogName())
      .append(getDogBreed())
      .append(getDogBio())
      .append(getProfileImageUrl())
      .append(getOwner())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Dog {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("dogName=" + String.valueOf(getDogName()) + ", ")
      .append("dogBreed=" + String.valueOf(getDogBreed()) + ", ")
      .append("dogBio=" + String.valueOf(getDogBio()) + ", ")
      .append("profileImageURL=" + String.valueOf(getProfileImageUrl()) + ", ")
      .append("owner=" + String.valueOf(getOwner()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DogNameStep builder() {
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
  public static Dog justId(String id) {
    return new Dog(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      dogName,
      dogBreed,
      dogBio,
      profileImageURL,
      owner);
  }
  public interface DogNameStep {
    BuildStep dogName(String dogName);
  }
  

  public interface BuildStep {
    Dog build();
    BuildStep id(String id);
    BuildStep dogBreed(String dogBreed);
    BuildStep dogBio(String dogBio);
    BuildStep profileImageUrl(String profileImageUrl);
    BuildStep owner(User owner);
  }
  

  public static class Builder implements DogNameStep, BuildStep {
    private String id;
    private String dogName;
    private String dogBreed;
    private String dogBio;
    private String profileImageURL;
    private User owner;
    @Override
     public Dog build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Dog(
          id,
          dogName,
          dogBreed,
          dogBio,
          profileImageURL,
          owner);
    }
    
    @Override
     public BuildStep dogName(String dogName) {
        Objects.requireNonNull(dogName);
        this.dogName = dogName;
        return this;
    }
    
    @Override
     public BuildStep dogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
        return this;
    }
    
    @Override
     public BuildStep dogBio(String dogBio) {
        this.dogBio = dogBio;
        return this;
    }
    
    @Override
     public BuildStep profileImageUrl(String profileImageUrl) {
        this.profileImageURL = profileImageUrl;
        return this;
    }
    
    @Override
     public BuildStep owner(User owner) {
        this.owner = owner;
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
    private CopyOfBuilder(String id, String dogName, String dogBreed, String dogBio, String profileImageUrl, User owner) {
      super.id(id);
      super.dogName(dogName)
        .dogBreed(dogBreed)
        .dogBio(dogBio)
        .profileImageUrl(profileImageUrl)
        .owner(owner);
    }
    
    @Override
     public CopyOfBuilder dogName(String dogName) {
      return (CopyOfBuilder) super.dogName(dogName);
    }
    
    @Override
     public CopyOfBuilder dogBreed(String dogBreed) {
      return (CopyOfBuilder) super.dogBreed(dogBreed);
    }
    
    @Override
     public CopyOfBuilder dogBio(String dogBio) {
      return (CopyOfBuilder) super.dogBio(dogBio);
    }
    
    @Override
     public CopyOfBuilder profileImageUrl(String profileImageUrl) {
      return (CopyOfBuilder) super.profileImageUrl(profileImageUrl);
    }
    
    @Override
     public CopyOfBuilder owner(User owner) {
      return (CopyOfBuilder) super.owner(owner);
    }
  }
  
}
