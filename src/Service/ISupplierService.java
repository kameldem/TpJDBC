package Service;

import Dao.SupplierDAO;
import Entity.Supplier;

import java.util.List;

public interface ISupplierService {

    void addSupplier(Supplier supplier);

    Supplier getSupplierById(Long id);

    List<Supplier> getAllSuppliers();

    void updateSupplier(Supplier supplier);

    void deleteSupplier(Long id);

}
