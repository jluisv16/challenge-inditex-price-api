package com.inditex.price.application.api;

import com.inditex.price.application.PriceService;
import com.inditex.price.application.dto.PriceDto;
import com.inditex.price.domain.Brand;
import com.inditex.price.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private Product product;
    private Brand brand;

    @BeforeEach
    public void setup() {
        // Inicializar MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();

        // Inicialización de datos comunes
        brand = new Brand(1L, "ZARA");
        product = new Product(35455L, "Product 35455");
    }

    @Test
    public void testFindValidPrices_10AM() throws Exception {

        PriceDto expectedPrice = new PriceDto(
                1L, 35455L, LocalDateTime.of(2023, 3, 14, 10, 0, 0), LocalDateTime.of(2023, 3, 14, 18, 0, 0),
                1, new BigDecimal("35.50"), "EUR");

        when(priceService.findValidPrices(1L, 35455L, LocalDateTime.of(2023, 3, 14, 10, 0, 0)))
                .thenReturn(Optional.of(expectedPrice));

        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2023-03-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testFindValidPrices_4PM() throws Exception {
        PriceDto expectedPrice = new PriceDto(
                1L, 35455L, LocalDateTime.of(2023, 3, 14, 16, 0, 0), LocalDateTime.of(2023, 3, 14, 18, 0, 0),
                1, new BigDecimal("50.00"), "EUR");

        when(priceService.findValidPrices(1L, 35455L, LocalDateTime.of(2023, 3, 14, 16, 0, 0)))
                .thenReturn(Optional.of(expectedPrice));

        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2023-03-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(50.00))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testFindValidPrices_9PM() throws Exception {
        PriceDto expectedPrice = new PriceDto(
                1L, 35455L, LocalDateTime.of(2023, 3, 14, 21, 0, 0), LocalDateTime.of(2023, 3, 14, 23, 59, 59),
                1, new BigDecimal("70.00"), "EUR");

        when(priceService.findValidPrices(1L, 35455L, LocalDateTime.of(2023, 3, 14, 21, 0, 0)))
                .thenReturn(Optional.of(expectedPrice));

        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2023-03-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(70.00))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testFindValidPrices_10AM_15th() throws Exception {
        PriceDto expectedPrice = new PriceDto(
                1L, 35455L, LocalDateTime.of(2023, 3, 15, 10, 0, 0), LocalDateTime.of(2023, 3, 15, 18, 0, 0),
                2, new BigDecimal("45.00"), "EUR");

        when(priceService.findValidPrices(1L, 35455L, LocalDateTime.of(2023, 3, 15, 10, 0, 0)))
                .thenReturn(Optional.of(expectedPrice));

        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2023-03-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(45.00))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testFindValidPrices_9PM_16th() throws Exception {


        PriceDto expectedPrice = new PriceDto(
                1L, 35455L, LocalDateTime.of(2023, 3, 16, 21, 0, 0), LocalDateTime.of(2023, 3, 16, 23, 59, 59),
                3, new BigDecimal("55.00"), "EUR");

        when(priceService.findValidPrices(1L, 35455L, LocalDateTime.of(2023, 3, 16, 21, 0, 0)))
                .thenReturn(Optional.of(expectedPrice));

        mockMvc.perform(get("/api/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("date", "2023-03-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(55.00))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }
}