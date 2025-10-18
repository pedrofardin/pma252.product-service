package store.product;

import java.util.List;

public class ProductParser {

    public static Product to(ProductIn in) {
        return in == null ? null :
            Product.builder()
                .name(in.name())
                .price(in.price())
                .unit(in.unit())
                .build();
    }

    public static ProductOut to(Product p) {
        return p == null ? null :
            new ProductOut(p.id(), p.name(), p.price(), p.unit());
    }

    public static List<ProductOut> to(List<Product> ps) {
        return ps == null ? null :
            ps.stream().map(ProductParser::to).toList();
    }

}
