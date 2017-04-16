package Daos;

import CRUDCliente.Cliente;
import ConnectionBD.ConnectionUtils;
import Exeptions.ClienteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonata
 */
public class DaoCliente {

    public static void inserir(Cliente cliente)
            throws SQLException, Exception {
        String sql = "INSERT INTO cliente (nome, cpf, telefone, email, enabled) "
                + "VALUES (?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getTelefone());
            statement.setString(4, cliente.getEmail());
            statement.setString(5, cliente.getEnabled());
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static void deletar(String cpf)
            throws SQLException, Exception {
        String sql = "UPDATE cliente SET enabled = ?"
                + " WHERE cpf = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, "false");
            statement.setString(2, cpf);

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static void alterar(Cliente cliente, String cpf)
            throws SQLException, Exception {
        String sql = "UPDATE cliente "
                + "SET nome = ?, "
                + "telefone = ?, "
                + "email = ?, "
                + "WHERE cpf = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getEmail());
            statement.setString(10, cpf);
            System.out.println(statement.toString());

            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute();
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static List<Cliente> pesquisar(String palavra)
            throws SQLException, Exception {

        String sql = "SELECT * FROM cliente "
                + " WHERE cliente.enabled = 'true';";

        Connection connection = null;
        PreparedStatement statement = null;
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + palavra + "%");

            System.out.println("Executando COMANDO SQL: " + sql);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }
                Cliente cliente = new Cliente();
                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getCpf());
                statement.setString(3, cliente.getTelefone());
                statement.setString(4, cliente.getEmail());
                statement.setString(5, cliente.getEnabled());
                System.out.println(statement.toString());

                listaClientes.add(cliente);
            }
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaClientes;
    }

    public static Cliente obter(String cpf)
            throws SQLException, Exception {
        String sql = "SELECT * FROM cliente WHERE cpf = ? AND "
                + "enabled = 'true';";
        PreparedStatement statement = null;
        Connection connection = null;

        statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        List<Cliente> listaClientes = (List<Cliente>) executarConsulta(sql);

        if (listaClientes != null && listaClientes.size() > 0) {
            return listaClientes.get(0);
        }

        return null;
    }

    public static List<Cliente> executarConsulta(String sql) throws
            ClienteException, SQLException, Exception {
        List<Cliente> listaClientes = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.createStatement();
            System.out.println("Executando CONSULTA SQL: " + sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }
                Cliente cliente = new Cliente();
                cliente.setCpf(result.getString("cpf"));
                cliente.setNome(result.getString("nome"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setEmail(result.getString("email"));
                cliente.setEnabled(result.getString("enabled"));
                listaClientes.add(cliente);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaClientes;
    }

    public static List<Cliente> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM cliente WHERE enabled = 'true'";

        return executarConsulta(sql);
    }

    public static Cliente retornarCliente(String cpf) throws
            SQLException, Exception {
        String sql = "SELECT * FROM cliente "
                + " WHERE cliente.cpf = ?";
        Cliente cliente = new Cliente();
        Connection connection = null;
        PreparedStatement statement = null;

        connection = ConnectionUtils.getConnection();
        statement = connection.prepareStatement(sql);

        statement.setString(1, cpf);

        System.out.println("Executando CONSULTA SQL: " + sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            cliente.setCpf(result.getString("cpf"));
            cliente.setNome(result.getString("nome"));
            cliente.setTelefone(result.getString("telefone"));
            cliente.setEmail(result.getString("email"));
            cliente.setEnabled(result.getString("enabled"));
            connection.close();
            return cliente;
        }

        return null;
    }
}
