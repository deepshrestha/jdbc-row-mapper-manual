package com.cibt.springapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cibt.springapp.dao.CustomerAddressBookDAO;
import com.cibt.springapp.models.AddressBook;
import com.cibt.springapp.models.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerAddressBookDAOImpl implements CustomerAddressBookDAO {
    
    @Autowired
    private JdbcTemplate template;

    @Override
	public List<Customer> fetchAll() {
        String sql = "SELECT c.id, a.id as address_book_id, c.customer_name, "
                    + "a.country, a.city, a.street_address, a.email, a.phone_number "
                    + "FROM tbl_customer c "
                    + "JOIN tbl_address_book a ON (c.address_book_id = a.id)";
        return template.query(sql, new RowMapper<Customer>() {

            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                /* Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setCustomerName(rs.getString("customer_name"));

                AddressBook addressBook = new AddressBook();
                addressBook.setId(rs.getInt("address_book_id"));
                addressBook.setCity(rs.getString("city"));
                addressBook.setCountry(rs.getString("country"));
                addressBook.setEmail(rs.getString("email"));
                addressBook.setPhoneNumber(rs.getString("phone_number"));
                addressBook.setStreetAddress(rs.getString("street_address"));
                customer.setAddressBook(addressBook);
                
                return customer; */

                // Method Chaining Builder Pattern Style
                return new Customer()
                    .setId(rs.getInt("id"))
                    .setCustomerName(rs.getString("customer_name"))
                    .setAddressBook(
                        new AddressBook()
                        .setId(rs.getInt("address_book_id"))
                        .setCity(rs.getString("city"))
                        .setCountry(rs.getString("country"))
                        .setEmail(rs.getString("email"))
                        .setPhoneNumber(rs.getString("phone_number"))
                        .setStreetAddress(rs.getString("street_address"))
                    );
			}
            
        });
	}

    @Override
    public int insert(Customer customer) {
        String sql = "INSERT INTO tbl_customer (customer_name, address_book_id) VALUES (?, ?)";

        return template.update(sql, new Object[] {
            customer.getCustomerName(),
            customer.getAddressBook().getId()
        });
    }

    @Override
    public int update(Customer customer) {
        String sql = "UPDATE tbl_customer SET customer_name=? WHERE id=?";

        return template.update(sql, new Object[] {
            customer.getCustomerName(),
            customer.getId()
        });
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM tbl_customer WHERE id=?";

        return template.update(sql, new Object[] {
            id
        });
    }

    @Override
    public int insert(final AddressBook addressBook) {
        
        /* String sql = "INSERT INTO tbl_address_book (country, city, street_address, email, phone_number) "
                    + "VALUES (?, ?, ? , ? , ?)";

        int result = template.update(sql, new Object[] {
            addressBook.getCountry(),
            addressBook.getCity(),
            addressBook.getStreetAddress(),
            addressBook.getEmail(),
            addressBook.getPhoneNumber()
        }); */

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        int result = template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String sql = "INSERT INTO tbl_address_book (country, city, street_address, email, phone_number) "
                    + "VALUES (?, ?, ? , ? , ?)";
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, addressBook.getCountry());
                statement.setString(2, addressBook.getCity());
                statement.setString(3, addressBook.getStreetAddress());
                statement.setString(4, addressBook.getEmail());
                statement.setString(5, addressBook.getPhoneNumber());
                return statement;
            }
        }, holder);
    
        long primaryKey = holder.getKey().longValue();
        addressBook.setId((int) primaryKey);
        
        return result;
    }

    @Override
    public int deleteAddressBook(int id) {
        String sql = "DELETE FROM tbl_address_book WHERE id=?";
        
        return template.update(sql, new Object[] {
            id
        });
    }

}