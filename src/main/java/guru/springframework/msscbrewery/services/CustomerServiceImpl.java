package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDto getCustomerById(UUID beerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName("Jordan Salas")
                .customerEmail("jsalas@email.com")
                .customerCell("987345123")
                .build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName(customerDto.getCustomerName())
                .customerEmail(customerDto.getCustomerEmail())
                .customerCell(customerDto.getCustomerCell())
                .build();
    }

    @Override
    public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
        // TODO: impl - would add a real impl to update customer
        return null;
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("Deleting a customer...");
    }
}
