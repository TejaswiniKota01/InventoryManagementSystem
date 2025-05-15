import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection conn;

    public InventoryDAO() throws SQLException {
        conn = DBConnection.getConnection();
        if (conn == null) throw new SQLException("Connection is null");
    }

    // Add item
    public void addItem(InventoryItem item) throws SQLException {
        String sql = "INSERT INTO inventory (name, quantity, price) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, item.getName());
        stmt.setInt(2, item.getQuantity());
        stmt.setDouble(3, item.getPrice());
        stmt.executeUpdate();
    }

    // Get all items
    public List<InventoryItem> getAllItems() throws SQLException {
        List<InventoryItem> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            InventoryItem item = new InventoryItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getDouble("price")
            );
            list.add(item);
        }
        return list;
    }

    // Update item
    public void updateItem(InventoryItem item) throws SQLException {
        String sql = "UPDATE inventory SET name = ?, quantity = ?, price = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, item.getName());
        stmt.setInt(2, item.getQuantity());
        stmt.setDouble(3, item.getPrice());
        stmt.setInt(4, item.getId());
        stmt.executeUpdate();
    }

    // Delete item by id
    public void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM inventory WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Search items by name
    public List<InventoryItem> searchItemsByName(String searchTerm) throws SQLException {
        List<InventoryItem> list = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE name LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + searchTerm + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(new InventoryItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("quantity"),
                rs.getDouble("price")
            ));
        }
        return list;
    }

    // Get total inventory value
    public double getTotalInventoryValue() throws SQLException {
        String sql = "SELECT SUM(quantity * price) AS total_value FROM inventory";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getDouble("total_value");
        }
        return 0.0;
    }
}
