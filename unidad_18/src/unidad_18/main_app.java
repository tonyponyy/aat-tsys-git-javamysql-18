package unidad_18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class main_app {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:33060?useTimezone=true&serverTimezone=UTC","root","123456");
			System.out.println("CONECTION OK");
			//informatica
			crea_esquema(conexion,"informatica");
			usar_esquema(conexion,"informatica");
			crea_tabla(conexion,"fabricantes","codigo int, nombre varchar(100),PRIMARY KEY(codigo)");
			crea_tabla(conexion,"articulos","codigo int, nombre varchar(100), precio int, fabricante int, foreign key (fabricante) references fabricantes(codigo) on delete cascade on update cascade, PRIMARY KEY(codigo)");
			String atributos_fabricantes = "codigo,nombre";
			agregar_registro(conexion,"fabricantes",atributos_fabricantes,"1,'Sony'");
			agregar_registro(conexion,"fabricantes",atributos_fabricantes,"2,'Epson'");
			agregar_registro(conexion,"fabricantes",atributos_fabricantes,"3,'Lenovo'");
			agregar_registro(conexion,"fabricantes",atributos_fabricantes,"4,'Aiwa'");
			String atributos_articulos = "codigo,nombre,precio,fabricante";
			agregar_registro(conexion,"articulos",atributos_articulos,"1,'CD Virgen',2,1");
			agregar_registro(conexion,"articulos",atributos_articulos,"2,'Impresora',200,2");
			agregar_registro(conexion,"articulos",atributos_articulos,"3,'Portatil',400,1");
			agregar_registro(conexion,"articulos",atributos_articulos,"4,'Radiocaset',80,4");
			
			mostrar_tabla(conexion,"articulos");
			mostrar_tabla(conexion,"fabricantes");
			//empleados
			crea_esquema(conexion,"empleados");
			usar_esquema(conexion,"empleados");
			crea_tabla(conexion,"departamentos","codigo int, nombre varchar(100), presupuesto int, PRIMARY KEY(codigo)");
			crea_tabla(conexion,"empleados","dni varchar(8), nombre varchar(100), apellidos varchar(255), cod_departamento int, foreign key (cod_departamento ) references departamentos(codigo) on delete cascade on update cascade, PRIMARY KEY(dni)");
			String atributos_departamentos = "codigo,nombre,presupuesto";
			agregar_registro(conexion,"departamentos",atributos_departamentos,"1,'Marketing',60000");
			agregar_registro(conexion,"departamentos",atributos_departamentos,"2,'Recursos humanos',30000");
			agregar_registro(conexion,"departamentos",atributos_departamentos,"3,'Reparaciones',120000");
			agregar_registro(conexion,"departamentos",atributos_departamentos,"4,'Asistencia tecnica',6000");
			String atributos_empleados = "dni,nombre,apellidos,cod_departamento";
			agregar_registro(conexion,"empleados",atributos_empleados,"'142334P','Julian','Carrascosa',1");
			agregar_registro(conexion,"empleados",atributos_empleados,"'654434K','Laura','Torque',2");
			agregar_registro(conexion,"empleados",atributos_empleados,"'662334I','Karen','Villalba',2");
			agregar_registro(conexion,"empleados",atributos_empleados,"'456454U','Isaura','Smith',4");
			
			mostrar_tabla(conexion,"departamentos");
			mostrar_tabla(conexion,"empleados");
			
			//almacenes
			crea_esquema(conexion,"almacenes");
			usar_esquema(conexion,"almacenes");
			crea_tabla(conexion,"almacenes","codigo int, lugar varchar(100), capacidad int, PRIMARY KEY(codigo)");
			crea_tabla(conexion,"cajas","num_referencia varchar(5), contenido varchar(100), valor int, almacen int, foreign key (almacen) references almacenes(codigo) on delete cascade on update cascade, PRIMARY KEY(num_referencia)");
			String atributos_almacenes = "codigo,lugar,capacidad";
			agregar_registro(conexion,"almacenes",atributos_almacenes,"1,'Tarragona',50");
			agregar_registro(conexion,"almacenes",atributos_almacenes,"2,'Reus',200");
			agregar_registro(conexion,"almacenes",atributos_almacenes,"3,'Valls',40");
			agregar_registro(conexion,"almacenes",atributos_almacenes,"4,'kosovo',60");
			String atributos_cajas = "num_referencia,contenido,valor,almacen";
			agregar_registro(conexion,"cajas",atributos_cajas,"1,'revistas',100,1");
			agregar_registro(conexion,"cajas",atributos_cajas,"2,'libros',10,1");
			agregar_registro(conexion,"cajas",atributos_cajas,"3,'pirotecnia',10,2");
			agregar_registro(conexion,"cajas",atributos_cajas,"4,'ladrillos',1000,3");
			
			mostrar_tabla(conexion,"almacenes");
			mostrar_tabla(conexion,"cajas");
			
			//peliculas
			crea_esquema(conexion,"peliculas");
			usar_esquema(conexion,"peliculas");
			crea_tabla(conexion,"peliculas","codigo int, nombre_pelicula varchar(100),calificacion_edad int,PRIMARY KEY(codigo)");
			crea_tabla(conexion,"salas","codigo int,nombre varchar(100), codigo_pelicula int,PRIMARY KEY(codigo),foreign key (codigo_pelicula) references peliculas(codigo) on delete cascade on update cascade");
			String atributos_peliculas = "codigo,nombre_pelicula,calificacion_edad";	
			agregar_registro(conexion,"peliculas",atributos_peliculas,"1,'Harry potter',12");
			agregar_registro(conexion,"peliculas",atributos_peliculas,"2,'DBZ resurrecio de freezer',7");
			agregar_registro(conexion,"peliculas",atributos_peliculas,"3,'Barbie',12");
			agregar_registro(conexion,"peliculas",atributos_peliculas,"4,'Deadpool',16");
			String atributos_salas = "codigo,nombre,codigo_pelicula";	
			agregar_registro(conexion,"salas",atributos_salas,"1,'Sala 1',1");
			agregar_registro(conexion,"salas",atributos_salas,"2,'Sala 2',1");
			agregar_registro(conexion,"salas",atributos_salas,"3,'Sala 3',3");
			agregar_registro(conexion,"salas",atributos_salas,"4,'Sala 4',4");
			
			mostrar_tabla(conexion,"peliculas");
			mostrar_tabla(conexion,"salas");
			
			//directores
			crea_esquema(conexion,"directores");
			usar_esquema(conexion,"directores");
			crea_tabla(conexion,"despachos","numero int, capacidad int, PRIMARY KEY(numero)");
			crea_tabla(conexion,"directores","dni varchar(8), nom_apels varchar(255), dni_jefe varchar(8),despacho int,foreign key (despacho) references despachos(numero) on delete cascade on update cascade, foreign key (dni_jefe ) references directores(dni) on delete cascade on update cascade, PRIMARY KEY(dni)");
			String atributos_despachos = "numero,capacidad";	
			agregar_registro(conexion,"despachos",atributos_despachos,"1,7");
			agregar_registro(conexion,"despachos",atributos_despachos,"2,8");
			agregar_registro(conexion,"despachos",atributos_despachos,"3,12");
			agregar_registro(conexion,"despachos",atributos_despachos,"4,6");
			String atributos_directores = "dni,nom_apels,despacho";
			agregar_registro(conexion,"directores",atributos_directores,"'1342324P','Jorge carbó',1");
			agregar_registro(conexion,"directores",atributos_directores,"'230309L','Alicia franco',1");
			agregar_registro(conexion,"directores",atributos_directores,"'789007Z','Zaida Remina',1");
			agregar_registro(conexion,"directores","dni,nom_apels,dni_jefe,despacho","'345345G','Shinji Ikari','1342324P',1");
			
			mostrar_tabla(conexion,"despachos");
			mostrar_tabla(conexion,"directores");
			
			//piezas proveedores
			crea_esquema(conexion,"piezas_proveedores");
			usar_esquema(conexion,"piezas_proveedores");
			crea_tabla(conexion,"piezas","codigo int, nombre varchar(100),PRIMARY KEY(codigo)");
			crea_tabla(conexion,"proveedores","codigo int, nombre varchar(100),PRIMARY KEY(codigo)");
			crea_tabla(conexion,"suministra","codigo_pieza int,codigo_proveedor int,precio double,PRIMARY KEY(codigo_pieza,codigo_proveedor),foreign key (codigo_pieza) references piezas(codigo) on delete cascade on update cascade, foreign key (codigo_proveedor) references proveedores(codigo) on delete cascade on update cascade");
			String atributos_piezas_proveedores = "codigo,nombre";
			agregar_registro(conexion,"piezas",atributos_piezas_proveedores,"1,'Tornillo 9mm'");
			agregar_registro(conexion,"piezas",atributos_piezas_proveedores,"2,'Tornillo 8mm'");
			agregar_registro(conexion,"piezas",atributos_piezas_proveedores,"3,'tuerca 3mm'");
			agregar_registro(conexion,"piezas",atributos_piezas_proveedores,"4,'Tornillo 2mm'");
			agregar_registro(conexion,"proveedores",atributos_piezas_proveedores,"1,'Sony'");
			agregar_registro(conexion,"proveedores",atributos_piezas_proveedores,"2,'Aiwa'");
			agregar_registro(conexion,"proveedores",atributos_piezas_proveedores,"3,'Sanyo'");
			agregar_registro(conexion,"proveedores",atributos_piezas_proveedores,"4,'Epson'");
			String atributos_suministra = "codigo_pieza,codigo_proveedor,precio";
			agregar_registro(conexion,"suministra",atributos_suministra,"1,1,20");
			agregar_registro(conexion,"suministra",atributos_suministra,"1,2,60");
			agregar_registro(conexion,"suministra",atributos_suministra,"2,1,80");
			agregar_registro(conexion,"suministra",atributos_suministra,"4,1,90");

			mostrar_tabla(conexion,"piezas");
			mostrar_tabla(conexion,"proveedores");
			
			//cientificos
			crea_esquema(conexion,"cientificos");
			usar_esquema(conexion,"cientificos");
			crea_tabla(conexion,"cientificos","dni varchar(8), nom_apels varchar(255), PRIMARY KEY(dni)");
			crea_tabla(conexion,"proyecto","id varchar(4), nombre varchar(255), horas int, PRIMARY KEY(id)");
			crea_tabla(conexion,"asignado_a","dni_cientifico varchar(8), id_proyecto varchar(4), foreign key (dni_cientifico) references cientificos(dni) on delete cascade on update cascade,  foreign key (id_proyecto  ) references proyecto(id) on delete cascade on update cascade, PRIMARY KEY(dni_cientifico, id_proyecto)");
			String atributos_cientificos = "dni,nom_apels";
			agregar_registro(conexion,"cientificos",atributos_cientificos,"'54334P','Carmen Sandiego'");
			agregar_registro(conexion,"cientificos",atributos_cientificos,"'23434P','Eloy cñamero'");
			agregar_registro(conexion,"cientificos",atributos_cientificos,"'89778P','Alberto Saviñatico'");
			agregar_registro(conexion,"cientificos",atributos_cientificos,"'23442P','Dolores Camelas'");
			String atributos_proyecto = "id,nombre,horas";
			agregar_registro(conexion,"proyecto",atributos_proyecto,"1,'Proyecto X',250");
			agregar_registro(conexion,"proyecto",atributos_proyecto,"2,'Proyecto Mayhem',250");
			agregar_registro(conexion,"proyecto",atributos_proyecto,"3,'Proyecto Jurasico',250");
			agregar_registro(conexion,"proyecto",atributos_proyecto,"4,'Proyecto 101',250");
			String atributos_asignado = "dni_cientifico,id_proyecto";
			agregar_registro(conexion,"asignado_a",atributos_asignado,"'54334P',1");
			agregar_registro(conexion,"asignado_a",atributos_asignado,"'54334P',3");
			agregar_registro(conexion,"asignado_a",atributos_asignado,"'89778P',2");
			agregar_registro(conexion,"asignado_a",atributos_asignado,"'23442P',1");
			
			mostrar_tabla(conexion,"cientificos");
			mostrar_tabla(conexion,"proyecto");
			mostrar_tabla(conexion,"asignado_a");
			
			//grandes almacenes
			crea_esquema(conexion,"grandes_almacenes");
			usar_esquema(conexion,"grandes_almacenes");
			crea_tabla(conexion,"cajeros","codigo int, nombre varchar(255),PRIMARY KEY(codigo)");
			crea_tabla(conexion,"maquinas_registradoras","codigo int, piso int,PRIMARY KEY(codigo)");
			crea_tabla(conexion,"productos","codigo int, nombre varchar(255), precio int,PRIMARY KEY(codigo)");
			crea_tabla(conexion,"suministra","codigo_cajeros int,codigo_maquinas int,codigo_productos int,PRIMARY KEY(codigo_cajeros,codigo_maquinas,codigo_productos),foreign key (codigo_cajeros) references cajeros(codigo) on delete cascade on update cascade,foreign key (codigo_maquinas) references maquinas_registradoras(codigo) on delete cascade on update cascade,foreign key (codigo_productos) references productos(codigo) on delete cascade on update cascade");
			String atributos_cajeros = "codigo,nombre";
			agregar_registro(conexion,"cajeros",atributos_cajeros,"1,'Carmen Sandiego'");
			agregar_registro(conexion,"cajeros",atributos_cajeros,"2,'Shinji Ikari'");
			agregar_registro(conexion,"cajeros",atributos_cajeros,"3,'Melondrio wilson'");
			agregar_registro(conexion,"cajeros",atributos_cajeros,"4,'Michael Escobar'");
			String atributos_maquinas = "codigo,piso";
			agregar_registro(conexion,"maquinas_registradoras",atributos_maquinas,"1,1");
			agregar_registro(conexion,"maquinas_registradoras",atributos_maquinas,"2,1");
			agregar_registro(conexion,"maquinas_registradoras",atributos_maquinas,"3,1");
			agregar_registro(conexion,"maquinas_registradoras",atributos_maquinas,"4,2");
			String atributos_productos = "codigo,nombre,precio";
			agregar_registro(conexion,"productos",atributos_productos,"1,'Pokemon escudo',60");
			agregar_registro(conexion,"productos",atributos_productos,"2,'El club de la lucha',60");
			agregar_registro(conexion,"productos",atributos_productos,"3,'Despertador seinfield',60");
			agregar_registro(conexion,"productos",atributos_productos,"4,'Taza Garfield',60");
			atributos_suministra = "codigo_cajeros,codigo_maquinas,codigo_productos";
			agregar_registro(conexion,"suministra",atributos_suministra,"1,1,1");
			agregar_registro(conexion,"suministra",atributos_suministra,"2,2,1");
			agregar_registro(conexion,"suministra",atributos_suministra,"2,3,4");
			agregar_registro(conexion,"suministra",atributos_suministra,"4,3,3");
			
			mostrar_tabla(conexion,"cajeros");
			mostrar_tabla(conexion,"maquinas_registradoras");
			mostrar_tabla(conexion,"productos");
			mostrar_tabla(conexion,"suministra");
			
			//investigadores 
			crea_esquema(conexion,"investigadores");
			usar_esquema(conexion,"investigadores");
			crea_tabla(conexion,"facultad","codigo int, nombre varchar(100), PRIMARY KEY(codigo)");
			crea_tabla(conexion,"investigadores","dni varchar(8), nom_apels varchar(255), facultad int, foreign key (facultad) references facultad(codigo) on delete cascade on update cascade, PRIMARY KEY(dni)");
			crea_tabla(conexion,"equipos","num_serie varchar(4), nombre varchar(100), facultad int, foreign key (facultad) references facultad(codigo) on delete cascade on update cascade, PRIMARY KEY(num_serie)");
			crea_tabla(conexion,"reserva","dni_cientifico varchar(8), num_equipo varchar(4),  comienzo date, fin date, foreign key (dni_cientifico) references investigadores(dni) on delete cascade on update cascade,  foreign key (num_equipo) references equipos(num_serie) on delete cascade on update cascade, PRIMARY KEY(dni_cientifico, num_equipo)");
			String atributos_facultad = "codigo,nombre";
			agregar_registro(conexion,"facultad",atributos_facultad,"1,'Sescelades'");
			agregar_registro(conexion,"facultad",atributos_facultad,"2,'Juridiques'");
			agregar_registro(conexion,"facultad",atributos_facultad,"3,'Lletras'");
			agregar_registro(conexion,"facultad",atributos_facultad,"4,'Sociales'");
			String atributos_investigadores = "dni,nom_apels,facultad";
			agregar_registro(conexion,"investigadores",atributos_investigadores,"'435345P','Jorge candimio',1");
			agregar_registro(conexion,"investigadores",atributos_investigadores,"'12122A','Rosa Bogavante',3");
			agregar_registro(conexion,"investigadores",atributos_investigadores,"'27887K','Eduardo Bonifacio',3");
			agregar_registro(conexion,"investigadores",atributos_investigadores,"'23233L','Emilio McQueen',2");
			String atributos_equipos = "num_serie,nombre,facultad";
			agregar_registro(conexion,"equipos",atributos_equipos,"1,'Equipo A',1");
			agregar_registro(conexion,"equipos",atributos_equipos,"2,'Equipo B',2");
			agregar_registro(conexion,"equipos",atributos_equipos,"3,'Equipo C',3");
			agregar_registro(conexion,"equipos",atributos_equipos,"4,'Equipo D',4");
			String atributos_reserva = "dni_cientifico,num_equipo,comienzo,fin";
			agregar_registro(conexion,"reserva",atributos_reserva,"'435345P',1,'2023-10-24','2023-10-25'");
			agregar_registro(conexion,"reserva",atributos_reserva,"'27887K',4,'2023-10-24','2023-10-25'");
			agregar_registro(conexion,"reserva",atributos_reserva,"'435345P',3,'2023-10-24','2023-10-25'");
			agregar_registro(conexion,"reserva",atributos_reserva,"'23233L',2,'2023-10-24','2023-10-25'");
			
			mostrar_tabla(conexion,"reserva");
			mostrar_tabla(conexion,"facultad");
			mostrar_tabla(conexion,"equipos");
			mostrar_tabla(conexion,"investigadores");
			
			conexion.close();
			
			
		}catch(SQLException | ClassNotFoundException ex ) {
			System.out.println("BAD CONNECTION--> "+ex);
		}
		
	}
	public static void mostrar_tabla(Connection conexion,String nombre_tabla) {
		System.out.println(negrita("\n*******  "+nombre_tabla+"  *******"));
		ejecuta_sentencia(conexion,"SELECT * FROM "+nombre_tabla,true,"");
	}
	public static void usar_esquema(Connection conexion,String nombre_esquema) {
		ejecuta_sentencia(conexion,"USE "+nombre_esquema,false,"usando el esquema "+nombre_esquema);
	}
	public static void crea_esquema(Connection conexion,String nombre_esquema) {
		ejecuta_sentencia(conexion,"CREATE DATABASE "+nombre_esquema,false,"esquema "+nombre_esquema+" creado correctamente");
	}
	public static void crea_tabla(Connection conexion,String nombre_tabla,String atributos_tabla) {
		ejecuta_sentencia(conexion,"CREATE TABLE "+nombre_tabla+"("+atributos_tabla+")",false,"tabla "+nombre_tabla+" creada correctamente");
	}
	public static void agregar_registro(Connection conexion,String nombre_tabla,String atributos_tabla,String valores_registro) {
		String sentencia = "insert into "+nombre_tabla+"("+atributos_tabla+") values ("+valores_registro+")";
		ejecuta_sentencia(conexion,sentencia,false,"registro agregado a la tabla "+nombre_tabla+" correctamente");
	}
	
	public static String negrita(String texto) {
		String string = "\033[1;97m"+texto+"\u001B[0m";
		return string;
	}
	
	public static boolean ejecuta_sentencia(Connection conexion,String sentencia, boolean lectura,String texto_correcto) {
		try {
			Statement st= conexion.createStatement();
			if (lectura) {
	            ResultSet resultados = st.executeQuery(sentencia);
	            ResultSetMetaData meta_data = resultados.getMetaData();
	            int num_columnas = meta_data.getColumnCount();
	            int num_registros = 0;
	            
				while (resultados.next()) {
					 for (int i = 1; i <= num_columnas; i++) {
		                    String nombre_columna = meta_data.getColumnName(i);
		                    Object valor = resultados.getObject(i);
		                    System.out.print(negrita(nombre_columna+": ") + valor+" ");
		                }
					 System.out.print("\n");
					 num_registros++;
				}
				System.out.println(num_registros+" resultados.");
			}else {
				st.executeUpdate(sentencia);
				System.out.println(texto_correcto);
			}
			
			return true;
		}catch(SQLException ex){
			System.out.println("Error--> "+ex);
			return false;
		}
	}
	
}
