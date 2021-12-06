package org.example.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TestRequestBody
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-12-05T23:27:42.477443+07:00[Asia/Novosibirsk]")
public class TestRequestBody {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("description")
    private String description;

    public TestRequestBody id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Object ID
     *
     * @return id
     */
    @ApiModelProperty(required = true, value = "Object ID")
    @NotNull
    @Valid
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TestRequestBody name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Object Name
     *
     * @return name
     */
    @ApiModelProperty(required = true, value = "Object Name")
    @NotNull
    @Size(min = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestRequestBody age(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * Object Age
     * minimum: 1
     * maximum: 99
     *
     * @return age
     */
    @ApiModelProperty(required = true, value = "Object Age")
    @NotNull
    @Min(1)
    @Max(99)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TestRequestBody description(String description) {
        this.description = description;
        return this;
    }

    /**
     * a lengthy Object Description
     *
     * @return description
     */
    @ApiModelProperty(value = "a lengthy Object Description")
    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestRequestBody testRequestBody = (TestRequestBody) o;
        return Objects.equals(this.id, testRequestBody.id) &&
                Objects.equals(this.name, testRequestBody.name) &&
                Objects.equals(this.age, testRequestBody.age) &&
                Objects.equals(this.description, testRequestBody.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TestRequestBody {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    age: ").append(toIndentedString(age)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
