package com.pakskiy.stocks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
//@ContextConfiguration(initializers = {StocksApplicationTests.Initializer.class})
class StocksApplicationTests {

//	private static final String DATABASE_NAME = "stocksTestDb";
//	@Container
//	static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:12.8")
//			.withDatabaseName(DATABASE_NAME)
//			.withUsername("postgres")
//			.withPassword("123456");
//
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
//		registry.add("spring.datasource.username", postgresqlContainer::getUsername);
//		registry.add("spring.datasource.password", postgresqlContainer::getPassword);
//	}
//
//	@Test
//	void contextLoads() {
//        System.out.println("logs from test");
//	}

//	@Test
//	@DisplayName("loadCompanies")
//	void checkCompaniesLoaderTest(){
//		List<SymbolsDto> symbolsDtoList = iexCloudConnector.loadSymbols();
//		List<CompanyEntity> companyList = symbolsDtoList.stream().map(SymbolsDto::toEntity).toList();
//		try {
//			companyRepository.saveAll(companyList);
//		}catch (Exception e){
//			System.out.println("startDownload::saveCompanies" + e);
//		}
//	}

}
