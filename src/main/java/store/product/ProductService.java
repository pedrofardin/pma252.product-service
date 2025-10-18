package store.product;

import java.util.List;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        if (null == product.name() || product.name().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Name is mandatory!"
            );
        }
        if (null == product.price() || product.price() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Price must be greater than zero!"
            );
        }
        if (null == product.unit() || product.unit().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Unit is mandatory!"
            );
        }

        return productRepository.save(
            new ProductModel(product)
        ).to();
    }

    public List<Product> findAll() {
        return StreamSupport.stream(
            productRepository.findAll().spliterator(), false)
            .map(ProductModel::to)
            .toList();
    }

    public Product findById(String id) {
        return productRepository.findById(id).map(ProductModel::to).orElse(null);
    }

    public void delete(String id) {
        productRepository.delete(new ProductModel().id(id));
    }

}
