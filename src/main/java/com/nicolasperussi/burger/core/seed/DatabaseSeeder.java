package com.nicolasperussi.burger.core.seed;

import com.nicolasperussi.burger.product.Product;
import com.nicolasperussi.burger.product.ProductCategory;
import com.nicolasperussi.burger.product.ProductRepository;
import com.nicolasperussi.burger.user.domain.Address;
import com.nicolasperussi.burger.user.domain.Client;
import com.nicolasperussi.burger.user.repositories.AddressRepository;
import com.nicolasperussi.burger.user.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev") // 1. Só vai rodar quando o application-dev.properties estiver ativo!
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("====== INICIANDO SEEDING DA BASE DE DADOS ======");

        // 2. Popula os Produtos (Preço em centavos!)
        if (productRepository.count() == 0) {
            Product p1 = new Product("Burger Supremo", "Blend de 180g, muito queijo cheddar e molho artesanal", 3500, ProductCategory.SANDWICH, true);
            Product p2 = new Product("Batata Rústica", "Batatas fritas com corte caseiro, alecrim e páprica", 1500, ProductCategory.SIDE, true);
            Product p3 = new Product("Suco de Laranja Natural", "Suco natural da fruta, sem conservantes 400ml", 1000, ProductCategory.DRINK, true);
            Product p4 = new Product("Brownie de Chocolate", "Brownie quentinho com pedaços de nozes", 1200, ProductCategory.DESSERT, true);

            productRepository.saveAll(List.of(p1, p2, p3, p4));
            System.out.println("-> Produtos populados com sucesso!");
        }

        // 3. Popula Clientes e Endereços vinculados
        if (clientRepository.count() == 0) {
            Client c1 = new Client("Nicolas Perussi", "nicolas@email.com", "senha123", "11999999999");
            clientRepository.save(c1); // Salva primeiro o pai para gerar o ID no banco

            // Instancia os endereços passando o cliente dono na Foreign Key
            Address a1 = new Address("Avenida Paulista", "1000", "01310-100", true, c1);
            Address a2 = new Address("Rua Augusta", "500", "01305-000", false, c1);

            addressRepository.saveAll(List.of(a1, a2));
            System.out.println("-> Clientes e Endereços populados com sucesso!");
        }

        System.out.println("====== SEEDING CONCLUÍDO COM SUCESSO ======");
    }
}