package com.amplifyframework.datastore.generated.model;

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

/** This is an auto generated class representing the DebugDog type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "DebugDogs", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class DebugDog implements Model {
  public static final QueryField ID = field("DebugDog", "id");
  public static final QueryField DOG_NAME = field("DebugDog", "dogName");
  public static final QueryField DOG_BREED = field("DebugDog", "dogBreed");
  public static final QueryField DOG_BIO = field("DebugDog", "dogBio");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String dogName;
  private final @ModelField(targetType="String") String dogBreed;
  private final @ModelField(targetType="String") String dogBio;
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
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private DebugDog(String id, String dogName, String dogBreed, String dogBio) {
    this.id = id;
    this.dogName = dogName;
    this.dogBreed = dogBreed;
    this.dogBio = dogBio;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      DebugDog debugDog = (DebugDog) obj;
      return ObjectsCompat.equals(getId(), debugDog.getId()) &&
              ObjectsCompat.equals(getDogName(), debugDog.getDogName()) &&
              ObjectsCompat.equals(getDogBreed(), debugDog.getDogBreed()) &&
              ObjectsCompat.equals(getDogBio(), debugDog.getDogBio()) &&
              ObjectsCompat.equals(getCreatedAt(), debugDog.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), debugDog.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDogName())
      .append(getDogBreed())
      .append(getDogBio())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("DebugDog {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("dogName=" + String.valueOf(getDogName()) + ", ")
      .append("dogBreed=" + String.valueOf(getDogBreed()) + ", ")
      .append("dogBio=" + String.valueOf(getDogBio()) + ", ")
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
  public static DebugDog justId(String id) {
    return new DebugDog(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      dogName,
      dogBreed,
      dogBio);
  }
  public interface DogNameStep {
    BuildStep dogName(String dogName);
  }
  

  public interface BuildStep {
    DebugDog build();
    BuildStep id(String id);
    BuildStep dogBreed(String dogBreed);
    BuildStep dogBio(String dogBio);
  }
  

  public static class Builder implements DogNameStep, BuildStep {
    private String id;
    private String dogName;
    private String dogBreed;
    private String dogBio;
    @Override
     public DebugDog build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new DebugDog(
          id,
          dogName,
          dogBreed,
          dogBio);
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
    private CopyOfBuilder(String id, String dogName, String dogBreed, String dogBio) {
      super.id(id);
      super.dogName(dogName)
        .dogBreed(dogBreed)
        .dogBio(dogBio);
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
  }
  
}
