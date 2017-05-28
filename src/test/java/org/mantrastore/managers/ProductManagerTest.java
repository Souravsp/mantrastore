package org.mantrastore.managers;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mantrastore.entities.Product;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(Arquillian.class)
public class ProductManagerTest {

    @Inject
    ProductManager productManager;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(ProductManager.class)
                .addClass(TestDataInserter.class)
                .addPackage(Product.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed()
    {
        Assert.assertNotNull(productManager);
    }

    @Test
    public void testListProducts() {
        List<Product> products = productManager.listProducts();
        assertThat(products.size(), is(5));
    }

//    @Test
//    public void testListProductsSortedAsc() {
//        List<Product> products = productManager.listProductsSorted(true);
//        assertThat(products.get(0).getPrice(), Is.<BigDecimal>is(BigDecimal.valueOf(10.00)));
//    }
//    @Test
//    public void testListProductsSortedDesc() {
//        List<Product> products = productManager.listProductsSorted(false);
//        assertThat(products.get(0).getPrice(), Is.<BigDecimal>is(BigDecimal.valueOf(50.00)));
//    }

    @Test
    public void testListProductsfilter() {
        List<Product> products = productManager.listProductsSorted("1", true);
        assertThat(products.size(), is(1));
    }
}
