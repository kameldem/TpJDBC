package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Supplier;

public class SupplierDAO {
    private Connection connection;

    public SupplierDAO(Connection connection) {
        this.connection = connection;
    }

    //  ajouter un fournisseur
    public void addSupplier(Supplier supplier) {
        try {
            String query = "INSERT INTO Supplier (number, name, email, adress) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, supplier.getSupplierNb());
                preparedStatement.setString(2, supplier.getName());
                preparedStatement.setString(3, supplier.getEmail());
                preparedStatement.setString(4, supplier.getAdress());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  récupérer tous les fournisseurs
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Supplier";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        suppliers.add(mapResultSetToSupplier(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    //  récupérer un fournisseur par son ID
    public Supplier getSupplierById(Long id) {
        Supplier supplier = null;
        try {
            String query = "SELECT * FROM Supplier WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        supplier = mapResultSetToSupplier(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    //  mettre à jour un fournisseur
    public void updateSupplier(Supplier supplier) {
        try {
            String query = "UPDATE Supplier SET number=?, name=?, email=?, adress=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, supplier.getSupplierNb());
                preparedStatement.setString(2, supplier.getName());
                preparedStatement.setString(3, supplier.getEmail());
                preparedStatement.setString(4, supplier.getAdress());
                preparedStatement.setLong(5, supplier.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // supprimer un fournisseur
    public void deleteSupplier(Long id) {
        try {
            String query = "DELETE FROM Supplier WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  mapper le résultat de la requête à un objet Supplier
    private Supplier mapResultSetToSupplier(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(resultSet.getLong("id"));
        supplier.setSupplierNb(resultSet.getInt("number"));
        supplier.setName(resultSet.getString("name"));
        supplier.setEmail(resultSet.getString("email"));
        supplier.setAdress(resultSet.getString("adress"));
        return supplier;
    }
}