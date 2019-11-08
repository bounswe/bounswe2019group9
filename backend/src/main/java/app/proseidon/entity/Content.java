package app.proseidon.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author ahmet.gedemenli
 */

@MappedSuperclass
public class Content {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "language_id")
  @NotNull
  private Integer languageId;

  @Column(name = "type_id")
  private Integer typeId;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "sound_url")
  private String soundUrl;

  public Content() {
  }

  public Content(Integer languageId, Integer typeId, String imageUrl, String soundUrl) {
    this.languageId = languageId;
    this.typeId = typeId;
    this.imageUrl = imageUrl;
    this.soundUrl = soundUrl;
  }

  public Integer getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Integer languageId) {
    this.languageId = languageId;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getSoundUrl() {
    return soundUrl;
  }

  public void setSoundUrl(String soundUrl) {
    this.soundUrl = soundUrl;
  }

  @Override
  public String toString() {
    return "Content{" +
           "languageId=" + languageId +
           ", typeId=" + typeId +
           ", imageUrl='" + imageUrl + '\'' +
           ", soundUrl='" + soundUrl + '\'' +
           '}';
  }
}
