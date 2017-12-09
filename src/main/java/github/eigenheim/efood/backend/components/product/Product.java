package github.eigenheim.efood.backend.components.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;
    private String manufacturer;
    private String imageUrl;

    @Lob
    private String description;

    protected Product() {}

    public Product(String name, String manufacturer,
                   String description, String imagePath) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.imageUrl = imagePath;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getId () {
        return id;
    }
}
