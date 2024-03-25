package guru.springframework.spring6webapp.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String publisherName;
    private String address;
    private String city;
    private String state;
    private String zip;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher publisher)) return false;

        if (getId() != null ? !getId().equals(publisher.getId()) : publisher.getId() != null) return false;
        if (getPublisherName() != null ? !getPublisherName().equals(publisher.getPublisherName()) : publisher.getPublisherName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(publisher.getAddress()) : publisher.getAddress() != null)
            return false;
        if (getCity() != null ? !getCity().equals(publisher.getCity()) : publisher.getCity() != null) return false;
        if (getState() != null ? !getState().equals(publisher.getState()) : publisher.getState() != null) return false;
        return getZip() != null ? getZip().equals(publisher.getZip()) : publisher.getZip() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPublisherName() != null ? getPublisherName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        return result;
    }
}
