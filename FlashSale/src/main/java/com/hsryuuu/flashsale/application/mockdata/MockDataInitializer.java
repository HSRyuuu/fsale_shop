package com.hsryuuu.flashsale.application.mockdata;

import com.hsryuuu.flashsale.category.Category;
import com.hsryuuu.flashsale.category.CategoryRepository;
import com.hsryuuu.flashsale.category.QCategory;
import com.hsryuuu.flashsale.member.Member;
import com.hsryuuu.flashsale.member.MemberRepository;
import com.hsryuuu.flashsale.member.MemberRole;
import com.hsryuuu.flashsale.member.QMember;
import com.hsryuuu.flashsale.product.Product;
import com.hsryuuu.flashsale.product.ProductRepository;
import com.hsryuuu.flashsale.product.ProductStatus;
import com.hsryuuu.flashsale.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class MockDataInitializer implements CommandLineRunner {
    private static final Random random = new Random();
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public void run(String... args) {
        log.info("MOCK-DATA-INITIALIZER START");
        long start = System.currentTimeMillis();
        List<Member> members = this.initMembers();
        List<Category> categories = this.initCategories();
        List<Product> products = this.initProducts(categories);
        long finish = System.currentTimeMillis();
        log.info("MOCK-DATA INSERT: {} members inserted ", members.size());
        log.info("MOCK-DATA INSERT: {} categories inserted ", categories.size());
        log.info("MOCK-DATA INSERT: {} products inserted ", products.size());
        log.info("MOCK-DATA INSERT FINISHED: time taken: {} ms", finish - start);
    }

    private List<Member> initMembers() {
        QMember member = QMember.member;
        Member findAny = queryFactory.selectFrom(member).fetchFirst();
        if (findAny != null) {
            return new ArrayList<>();
        }
        List<Member> members = new ArrayList<>();
        members.add(new Member(null, "ADMIN", getRandomString("ADMIN"), "admin@example.com", MemberRole.ADMIN, "ADMIN", "010-1111-2222", null, null));
        members.add(new Member(null, "John", getRandomString("John"), "John@example.com", MemberRole.USER, "John123", "010-2222-3333", null, null));
        members.add(new Member(null, "Smith", getRandomString("Smith"), "Smith@example.com", MemberRole.USER, "Smith123", "010-3333-4444", null, null));

        return memberRepository.saveAll(members);
    }

    private List<Category> initCategories() {
        QCategory category = QCategory.category;
        Category findAny = queryFactory.selectFrom(category).fetchFirst();
        if (findAny != null) {
            return new ArrayList<>();
        }

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(null, "Electronics", "Electronics Description...", null, null, null));
        categories.add(new Category(null, "Clothes", "Clothes Description...", null, null, null));
        categories.add(new Category(null, "Beauty & Personal Care", "Beauty & Personal Care Description...", null, null, null));
        categories.add(new Category(null, "Sports & Outdoor", "Sports & Outdoor Description...", null, null, null));
        categories.add(new Category(null, "Home & Kitchen", "Home & Kitchen Description...", null, null, null));

        return categoryRepository.saveAll(categories);
    }

    private List<Product> initProducts(List<Category> categories) {
        long start = System.currentTimeMillis();
        QProduct product = QProduct.product;
        Product findAny = queryFactory.selectFrom(product).fetchFirst();
        if (findAny != null) {
            return new ArrayList<>();
        }

        final int PRODUCT_DATA_AMOUNT = 1000;

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < PRODUCT_DATA_AMOUNT; i++) {
            int randomIndex = random.nextInt(categories.size());
            Category category = categories.get(randomIndex);
            products.add(Product.builder()
                    .name(category.getName() + "Product-" + i)
                    .price(new BigDecimal(getRandomLong(1000L, 1000000L)))
                    .sku("sku-" + category.getName())
                    .status(ProductStatus.AVAILABLE)
                    .stockQuantity(randomIndex * 10)
                    .category(category)
                    .build());
        }
        long end = System.currentTimeMillis();
        log.info("PRODUCT Insert Finished: time taken: {} ms", end - start);
        return productRepository.saveAll(products);
    }

    private String getRandomString(String prefix) {
        return prefix + "_" + UUID.randomUUID();
    }

    private Long getRandomLong(Long min, Long max) {
        return min + random.nextLong(max);
    }
}
