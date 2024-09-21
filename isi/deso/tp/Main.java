/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package isi.deso.tp;

import java.util.ArrayList;
import java.util.List;

import isi.deso.tp.dao.ItemsPedidoMemory;
import isi.deso.tp.exception.ItemNoEncontradoException;
import isi.deso.tp.model.BebidaAlcoholica;
import isi.deso.tp.model.BebidaSinAlcohol;
import isi.deso.tp.model.Categoria;
import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Comida;
import isi.deso.tp.model.Coordenada;
import isi.deso.tp.model.ItemMenu;
import isi.deso.tp.model.ItemPedido;
import isi.deso.tp.model.Pedido;
import isi.deso.tp.model.Vendedor;
import isi.deso.tp.service.GestorCliente;
import isi.deso.tp.service.GestorVendedor;


public class Main {

  public static void main(String[] args) throws ItemNoEncontradoException {
    GestorVendedor gestorVendedor = new GestorVendedor();
    GestorCliente gestorCliente = new GestorCliente();

    // Creando los 3 vendedores
    Vendedor v1 = gestorVendedor.crearVendedor(1, "Roticeria Pampita", "San luis 34", new Coordenada(1, 2));
    Vendedor v2 = gestorVendedor.crearVendedor(2, "Polleria Don Pechuga", "Paraguay 444", new Coordenada(2, 3));
    Vendedor v3 = gestorVendedor.crearVendedor(3, "Polleria Alitas", "Vicente 23", new Coordenada(3, 6));

    // Creando lista de vendedores
    List<Vendedor> listaVendedores = new ArrayList<>();

    // Agregando los 3 vendedores a la lista
    listaVendedores.add(v1);
    listaVendedores.add(v2);
    listaVendedores.add(v3);

    System.out.print("Vendedores filtrados\n");

    // Filtrando vendedores con Id 1
    List<Vendedor> listaVendedoresFiltradoID = gestorVendedor.filterVendedoresId(listaVendedores, 1);
    listaVendedoresFiltradoID.forEach(vendedor -> System.out.println(vendedor));

    // Filtrando vendedores con Nombre Polleria Don Pechuga
    List<Vendedor> listaVendedoresFiltradoNombre = gestorVendedor.filterVendedoresNombre(listaVendedores, "Polleria Don Pechuga");
    listaVendedoresFiltradoNombre.forEach(vendedor -> System.out.println(vendedor));

    // Creando los 3 clientes
    Cliente c1 = gestorCliente.crearClientes(1, "Lucca", "2043350012", "lsch@gmail.com", "San Luis 34", new Coordenada(1, 1));
    Cliente c2 = gestorCliente.crearClientes(2, "Pedro", "2042250012", "pedrito@gmail.com", "Salta 322", new Coordenada(2, 3));
    Cliente c3 = gestorCliente.crearClientes(2, "Patricio", "2042255512", "p@gmail.com", "Peron 2", new Coordenada(4, 4));

    // Creando lista de clientes
    List<Cliente> listaClientes = new ArrayList<>();

    // Agregando los 3 clientes a la lista
    listaClientes.add(c1);
    listaClientes.add(c2);
    listaClientes.add(c3);

    System.out.print("Clientes filtrados\n");

    // Filtrando clientes con Id 1
    List<Cliente> listaClienteFiltradoID = gestorCliente.filterClienteId(listaClientes, 1);
    listaClienteFiltradoID.forEach(cliente -> System.out.println(cliente));

    // Filtrando clientes con Nombre Pedro
    List<Cliente> listaClienteFiltradoNombre = gestorCliente.filterClienteNombre(listaClientes, "Pedro");
    listaClienteFiltradoNombre.forEach(cliente -> System.out.println(cliente));

    // Calculando distancia entre Vendedor y Cliente
    System.out.print("Distancia entre " + v1 + " y " + c1 + " es: " + v1.distancia(c1) + " km \n");

    System.out.print("Vendedores Actuales\n");

    listaVendedores.forEach(vendedor -> System.out.println(vendedor));

    // Eliminando vendedores con Id 1
    gestorVendedor.deleteVendedoresId(listaVendedores, 1);

    // Eliminando vendedores con Nombre Polleria Don Pechuga
    gestorVendedor.deleteVendedoresNombre(listaVendedores, "Polleria Don Pechuga");

    System.out.print("Vendedores luego de eliminacion\n");

    // Mostrando vendedores
    listaVendedores.forEach(vendedor -> System.out.println(vendedor));

    System.out.print("Clientes Actuales\n");

    listaClientes.forEach(cliente -> System.out.println(cliente));

    // Eliminando clientes con Id 1
    gestorCliente.deleteClienteId(listaClientes, 1);

    // Eliminando clientes con Nombre Pedro
    gestorCliente.deleteClienteNombre(listaClientes, "Pedro");

    System.out.print("Clientes luego de eliminacion\n");

    // Mostrando clientes
    listaClientes.forEach(cliente -> System.out.println(cliente));
    //
    Vendedor v4 = gestorVendedor.crearVendedor(1, "Roticeria Pampita", "San luis 34", new Coordenada(1, 2));
    Cliente c4 = gestorCliente.crearClientes(4, "Patricio", "2042255512", "p@gmail.com", "Peron 2", new Coordenada(4, 4));
    Pedido p1= new Pedido(c4,1);
    Categoria cat1= new Categoria(1,"Bebidas");
    Categoria cat2= new Categoria(2,"Carnes");
    Categoria cat3= new Categoria(3,"Postre");
    ItemMenu i1= new BebidaAlcoholica(500,1,102,"Santa Julia","vino tinto",7000.0,cat1);
    i1.setVendedor(v4);
    ItemMenu i2= new BebidaSinAlcohol(500, 1.5, 101, "Sprite", "Bebida gaseosa limon", 2500.0, cat1);
    i2.setVendedor(v4);
    ItemMenu i3=new Comida(50,true,false, 0.250,103,"Flan","Flan casero", 2500.0,cat3);
    i3.setVendedor(v4);
    v4.agregarPlato(i1);
    v4.agregarPlato(i2);
    v4.agregarPlato(i3);
    ItemPedido iP1= new ItemPedido(1,i2, 1, 2500.0);
    ItemPedido iP2= new ItemPedido(2,i1, 1, 7000.0);
    ItemPedido iP3= new ItemPedido(3,i3, 1, 2500.0);
    iP1.setPedido(p1);
    iP2.setPedido(p1);
      iP3.setPedido(p1);
      List<ItemPedido> listaPedido=new ArrayList<>();
      listaPedido.add(iP1);
    listaPedido.add(iP2);
    listaPedido.add(iP3);
    ItemsPedidoMemory detalleP=new ItemsPedidoMemory();
    detalleP.setLista(listaPedido);
  }
}