package app.proseidon;

/**
 * @author ahmet.gedemenli
 */

public class Content {

  private Integer languageId;

  private Integer typeId;

  private String imageUrl;

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
