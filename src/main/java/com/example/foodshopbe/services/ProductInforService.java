package com.example.foodshopbe.services;

import com.example.foodshopbe.dtos.ProductsInforDTO;
import com.example.foodshopbe.exceptions.DataNotFoundException;
import com.example.foodshopbe.models.ProductInfor;
import com.example.foodshopbe.repositories.ProductInforRepository;
import com.example.foodshopbe.services.Imp.IProductInforService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductInforService implements IProductInforService {

    private final ProductInforRepository productInforRepository;
    @Override
    public ProductInfor createProductInfor(ProductsInforDTO productsInforDTO) {
        ProductInfor newProductInfor = ProductInfor
                .builder()
                .name(productsInforDTO.getName())
                .material(productsInforDTO.getMaterial())
                .amount(productsInforDTO.getAmount())
                .expiratetionDate(productsInforDTO.getExpiratetionDate())
                .storageMethod(productsInforDTO.getStorageMethod())
                .saferyInstruc(productsInforDTO.getSaferyInstruc())
                .build();

        return productInforRepository.save(newProductInfor);

    }

    @Override
    public ProductInfor getProductInforById(int id) throws Exception {
        return productInforRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Khong tim thay product infor voi id : " + id));
    }

    @Override
    public List<ProductInfor> getAllProductInfor() {
        return productInforRepository.findAll();
    }

    @Override
    public ProductInfor updateProductInfor(int id, ProductsInforDTO productsInforDTO) throws Exception {
        Optional<ProductInfor> optionalProductInfor = productInforRepository.findById(id);
        if (!optionalProductInfor.isPresent()) {
            throw new Exception("Không tìm thấy ProductInfor với id: " + id);
        }
        ProductInfor existingProductInfor = optionalProductInfor.get();
        existingProductInfor.setName(productsInforDTO.getName());
        existingProductInfor.setAmount(productsInforDTO.getAmount());
        existingProductInfor.setMaterial(productsInforDTO.getMaterial());
        existingProductInfor.setExpiratetionDate(productsInforDTO.getExpiratetionDate());
        existingProductInfor.setStorageMethod(productsInforDTO.getStorageMethod());
        existingProductInfor.setSaferyInstruc(productsInforDTO.getSaferyInstruc());

        return productInforRepository.save(existingProductInfor);
    }

    @Override
    public void deleteProductInfor(int id) {
       productInforRepository.deleteById(id);
    }
}
