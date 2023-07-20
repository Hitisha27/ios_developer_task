import java.util.List;

public class Lecture {
    private int lectureID;
    private String lectureName;
    private String lectureIconURL;
    private int lectureDurationMinutes;
    private List<Integer> topics;

    public int getLectureDurationMinutes() {
        return lectureDurationMinutes;
    }

    public int getLectureID() {
        return lectureID;
    }

    public List<Integer> getTopics() {
        return topics;
    }

    public String getLectureName() {
        return lectureName;
    }

    public String getLectureIconURL() {
        return lectureIconURL;
    }

    public void setLectureDurationMinutes(int lectureDurationMinutes) {
        this.lectureDurationMinutes = lectureDurationMinutes;
    }

    public void setLectureIconURL(String lectureIconURL) {
        this.lectureIconURL = lectureIconURL;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public void setTopics(List<Integer> topics) {
        this.topics = topics;
    }
}

