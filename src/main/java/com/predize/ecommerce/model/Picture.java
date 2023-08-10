package com.predize.ecommerce.model;

import com.predize.ecommerce.util.ImageUtil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_fk")
    private Product product;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    public byte[] getImageData() {
        return ImageUtil.decompressImage(this.imageData);
    }
}
