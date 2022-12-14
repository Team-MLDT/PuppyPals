package com.amplifyframework.datastore.generated.model;

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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField USERNAME = field("User", "username");
  public static final QueryField USER_EMAIL = field("User", "userEmail");
  public static final QueryField PROFILE_IMAGE_URL = field("User", "profileImageURL");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String", isRequired = true) String userEmail;
  private final @ModelField(targetType="String") String profileImageURL;
  private final @ModelField(targetType="Dog") @HasMany(associatedWith = "owner", type = Dog.class) List<Dog> dogs = null;
  private final @ModelField(targetType="Event") @HasMany(associatedWith = "host", type = Event.class) List<Event> eventsHosted = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getUserEmail() {
      return userEmail;
  }
  
  public String getProfileImageUrl() {
      return profileImageURL;
  }
  
  public List<Dog> getDogs() {
      return dogs;
  }
  
  public List<Event> getEventsHosted() {
      return eventsHosted;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String username, String userEmail, String profileImageURL) {
    this.id = id;
    this.username = username;
    this.userEmail = userEmail;
    this.profileImageURL = profileImageURL;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUsername(), user.getUsername()) &&
              ObjectsCompat.equals(getUserEmail(), user.getUserEmail()) &&
              ObjectsCompat.equals(getProfileImageUrl(), user.getProfileImageUrl()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getUserEmail())
      .append(getProfileImageUrl())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("userEmail=" + String.valueOf(getUserEmail()) + ", ")
      .append("profileImageURL=" + String.valueOf(getProfileImageUrl()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
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
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      userEmail,
      profileImageURL);
  }
  public interface UsernameStep {
    UserEmailStep username(String username);
  }
  

  public interface UserEmailStep {
    BuildStep userEmail(String userEmail);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep profileImageUrl(String profileImageUrl);
  }
  

  public static class Builder implements UsernameStep, UserEmailStep, BuildStep {
    private String id;
    private String username;
    private String userEmail;
    private String profileImageURL;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          username,
          userEmail,
          profileImageURL);
    }
    
    @Override
     public UserEmailStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep userEmail(String userEmail) {
        Objects.requireNonNull(userEmail);
        this.userEmail = userEmail;
        return this;
    }
    
    @Override
     public BuildStep profileImageUrl(String profileImageUrl) {
        this.profileImageURL = profileImageUrl;
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
    private CopyOfBuilder(String id, String username, String userEmail, String profileImageUrl) {
      super.id(id);
      super.username(username)
        .userEmail(userEmail)
        .profileImageUrl(profileImageUrl);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder userEmail(String userEmail) {
      return (CopyOfBuilder) super.userEmail(userEmail);
    }
    
    @Override
     public CopyOfBuilder profileImageUrl(String profileImageUrl) {
      return (CopyOfBuilder) super.profileImageUrl(profileImageUrl);
    }
  }
  
}
