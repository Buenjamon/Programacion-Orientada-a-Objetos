package clasesyobjetos; // Paquete en el que se encuentran todas las clases del proyecto, como todo anda en el mismo paquete no se importan las demás clases

import java.util.ArrayList; 
// Biblioteca para crear matrices mas flexibles, en las que el tamaño se adapta al numero de objetos almacenados y los elementos son mas faciles de modificar
import java.util.Scanner; // Scanner

public class InterfazUsuario {
    private static ArrayList<Usuario> usuarios = Usuario.getListaUsuarios(); 
        // La lista anterior es una lista dinámica creada dentro de esta clase para acceder a los usuarios ya creados dentor de la clase Usuario
    private static ArrayList<Proyecto> proyectos = new ArrayList<>(); //Lista vacia de proyectos
    // Las listas anteriores son estaticas, significa que no pertenecen a ningun objeto y que se puede acceder a ellas sin instanciar la clase
    private static Scanner scanner = new Scanner(System.in);   // Iniciar el scanner
    private static Usuario usuarioActual = null; // Guardar el usuario que inicio sesion, esta variable vuelve a null cuando cierras sesion
    //Menu principal
    public static void main(String[] args) { 
        while (true) {
            System.out.println("\nBienvenido al Gestor de Tareas");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = obtenerEntradaNumerica();
            switch (opcion) { //Switch para navegar entre las opciones
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    System.exit(0); // terminar
                default:
                    System.out.println("Opcion invalida. Ingrese 1 o 2."); //Manejo de entradas no validas
            }
        }
    }
    //Inicio de sesion con un correo ya existente entre los usuarios
    private static void iniciarSesion() {
        while (true) { //Bucle para no salir del menú hasta que se requiera
            System.out.print("\nIngrese su correo(deje el espacio en blanco si desea volver): ");
            String correo = scanner.nextLine(); // Entradas del usuario, sentencia constante del programa

            usuarioActual = buscarUsuarioPorCorreo(correo); // Método para verificar que el usuario se encuentra en la lista
            if (correo.isEmpty()) return; //Cancelar la operación mediante una entrada vacía
            if (usuarioActual == null) {
                System.out.println("El correo proporcionado no coincide con ninguna cuenta existente.");
                continue; 
            }

            while (true) {
                System.out.print("Ingrese su contrasena: ");
                String contraseña = scanner.nextLine();

                if (!usuarioActual.getContraseña().equals(contraseña)) {
                    System.out.println("Contrasena incorrecta. Intentarlo de nuevo?");
                        int opcion; //crear la variable opción
                        do {
                            System.out.println("1. Reintentar");
                            System.out.println("2. Salir");
                            System.out.print("Seleccione una opcion: ");
                            opcion = obtenerEntradaNumerica();
                        
                            if (opcion != 1 && opcion != 2) { //Si la opcion no es 1 o 2 imprime lo siguiente
                                System.out.println("Opcion invalida.");
                            }
                          } while (opcion != 1 && opcion != 2); // Y vuelve a intentarlo

                    if (opcion == 2) return; // volver al menu de inicio
                } else {
                    // Esta parte esta aqui para que solo se imprima una vez, al iniciar sesion
                    System.out.println("\nBienvenido al gestor de tareas, " + usuarioActual.getNombre() + "!");
                    menuPrincipal();
                    return;
                }
            }
        }
    }
    // Segundo menu
    private static void menuPrincipal() {
        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Crear Proyecto");
            System.out.println("2. Mis Proyectos");
            System.out.println("3. Cerrar Sesion");
            System.out.print("Seleccione una opcion: ");

            int opcion = obtenerEntradaNumerica(); // Que hacer segun la entrada del usuario
            switch (opcion) {
                case 1:
                    crearProyecto();
                    break;
                case 2:
                    misProyectos();
                    break;
                case 3:
                    System.out.println("Sesion terminada.");
                    usuarioActual = null;
                    return;
                default:
                    System.out.println("Opcion invalida. Ingrese 1, 2 o 3."); // Errores
            }
        }
    }

    private static void crearProyecto() {
        System.out.println("\nNUEVO PROYECTO");
        System.out.print("Ingrese el nombre del proyecto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripcion del proyecto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Que prioridad tendra el proyecto?: ");
        String prioridad = scanner.nextLine();
        String estado = "Activo"; // El proyecto se marca automaticamente como activo
        // Crear el nuevo proyecto
        Proyecto proyecto = new Proyecto(nombre, descripcion, prioridad, estado);
        
        // Equipo del Proyecto
         ArrayList<String> equipoAsignado = new ArrayList<>();equipoAsignado.add(usuarioActual.getNombre()); // El creador se añade automáticamente
         // Escribir los nombres del equipo
         System.out.print("Ingresa los nombres del equipo (separados por comas): ");
         String personasInput = scanner.nextLine();
        // Quitar espacios en blanco de los valores ingresados
        if (personasInput != null && !personasInput.trim().isEmpty()) { // .trin sirve para quitar los espacios en blanco entre lo valores de la lista
           String[] nombresArray = personasInput.split(","); // lista de personas que divide los valores gracias a las comas

        // Agregar los nombres eliminando espacios extra
        for (String miembro : nombresArray) { 
            String nombreLimpio = miembro.trim();
            if (!nombreLimpio.isEmpty()) { // Evita agregar cadenas vacías
                equipoAsignado.add(nombreLimpio);
            }
        }
    }

    // Asignar la lista de personas al proyecto
    proyecto.setEquipoAsignado(equipoAsignado);

    // Agregar el proyecto a la lista de proyectos
    proyectos.add(proyecto);

    System.out.println("Proyecto creado exitosamente. Para modificarlo o asignar tareas vaya a 'Mis Proyectos'.");
    }
    
    private static void misProyectos() {
        // No hay proyectos
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos asignados a esta cuenta de usuario.");
            return;
        }

        System.out.println("\nSus Proyectos:");
        boolean tieneProyectos = false;
        // Listar proyectos disponilbes
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getEquipoAsignado().contains(usuarioActual.getNombre())) {  // Verificar si el usuario actual está en la lista de personas asignadas
                System.out.println("- " + proyecto.getNombre()); // Si se encuentra un proyecto en el que si esta, imprime el nombre del proyecto
                tieneProyectos = true;
            }
        }
        // Sin proyectos
        if (!tieneProyectos) {
            System.out.println("No tiene ningun proyecto creado.");
            return;
        }

        System.out.print("\nIngrese el nombre del proyecto para seleccionarlo o presione Enter para volver: ");
        String nombreProyecto = scanner.nextLine();

        if (nombreProyecto.isEmpty()) return; //volver si presionas intro

        Proyecto proyectoSeleccionado = buscarProyectoPorNombre(nombreProyecto);
        // Entradas incorrectas
        if (proyectoSeleccionado == null) {
            System.out.println("Proyecto no encontrado. Ingrese un nombre valido.");
            return;
        }

        // Si el usuario intenta acceder a un proyecto al que no tiene acceso
        if (!proyectoSeleccionado.getEquipoAsignado().contains(usuarioActual.getNombre())) {
            System.out.println("Acceso denegado. No está asignado a este proyecto.");
            return;
        }

        menuProyecto(proyectoSeleccionado);
    }
        // Menu 3
    private static void menuProyecto(Proyecto proyecto) {
        while (true) {
            System.out.println("\nProyecto: " + proyecto.getNombre());
            System.out.println("1. Modificar detalles del proyecto");
            System.out.println("2. Ver detalles del proyecto");
            System.out.println("3. Menu de Tareas");
            System.out.println("4. Eliminar Proyecto");
            System.out.println("5. Regresar");
            System.out.println("6. Seleccionar otro proyecto");
            System.out.print("Seleccione una opcion: ");

            int opcion = obtenerEntradaNumerica();
            switch (opcion) {
                case 1:
                    modificarProyecto(proyecto);
                    break;
                case 2:
                    verDetallesProyecto(proyecto);
                    break;
                case 3:
                    menuTareas(proyecto);
                    break;
                case 4:
                    eliminarProyecto(proyecto);
                    return;
                case 5:
                    return;
                case 6:
                    misProyectos();
                default:
                    System.out.println("Opcion invalida. Ingrese un numero entre 1 y 5.");
            }
        }
    }
    
    private static void modificarProyecto(Proyecto proyecto) {
        System.out.println("\nMODIFICAR DETALLES DEL PROYECTO");
        System.out.println("Si quieres conservar un dato como ya estaba tan solo presiona enter");
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) proyecto.setNombre(nombre); //mantener datos en caso de intro

        System.out.print("Nueva descripcion: ");
        String descripcion = scanner.nextLine();
        if (!descripcion.isEmpty()) proyecto.setDescripcion(descripcion);

        System.out.print("Nueva prioridad: ");
        String prioridad = scanner.nextLine();
        if (!prioridad.isEmpty()) proyecto.setPrioridad(prioridad);

        System.out.print("Nuevo estado (si no lo haz modificado antes el proyecto estara activo: ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) proyecto.setEstado(estado);
        
        System.out.println("\nProyecto modificado con exito.");
    }

    private static void verDetallesProyecto(Proyecto proyecto) {
        System.out.println("\nDETALLES DEL PROYECTO");
        System.out.println("Nombre: " + proyecto.getNombre());
        System.out.println("Descripcion: " + proyecto.getDescripcion());
        System.out.println("Prioridad: " + proyecto.getPrioridad());
        System.out.println("Estado: " + proyecto.getEstado());
        System.out.print("Equipo Asignado: ");
        if (proyecto.getEquipoAsignado().isEmpty()) {
            System.out.println("No hay usuarios asignados.");
            } else {
                System.out.println(String.join(", ", proyecto.getEquipoAsignado()));
            }
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
     }
    // Cuarto menu
    private static void menuTareas(Proyecto proyecto) {
        while (true){
            System.out.println("\nMENU DE TAREAS");
            System.out.println("1. Nueva tarea");
            System.out.println("2. Ver tareas existentes");
            System.out.println("3. Modificar tareas");
            System.out.println("4. Eliminar tareas");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una opcion: ");

            int opcion = obtenerEntradaNumerica();
            switch (opcion) {
                case 1:
                    crearTarea(proyecto);
                    break;
                case 2:
                    listarTareas(proyecto);
                    break;
                case 3:
                    modificarTarea(proyecto);
                    break;
                case 4:
                    eliminarTarea(proyecto);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opcion invalida. Ingrese un número entre 1 y 5.");
            }
        }
    }
    private static void crearTarea(Proyecto proyecto) {
        System.out.println("\nNUEVA TAREA");

        System.out.print("Ingresa el nombre de la tarea: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingresa la descripcion de la tarea: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingresa la prioridad de la tarea: ");
        String prioridad = scanner.nextLine();
        System.out.print("Ingresa el estado de la tarea: ");
        String estado = scanner.nextLine();
        System.out.print("Ingresa la fecha de vencimiento de la tarea: ");
        String fechaVencimiento = scanner.nextLine();
        Tarea nuevaTarea = new Tarea(nombre, descripcion, prioridad, estado, fechaVencimiento);
        proyecto.getTareas().add(nuevaTarea);

        System.out.println("Tarea creada exitosamente y agregada al proyecto '" + proyecto.getNombre() + "'.");
        }

    private static void listarTareas(Proyecto proyecto) {
        // Si la lista de tareas esta vacia:
        if (proyecto.getTareas().isEmpty()) {
            System.out.println("\nNo hay tareas asignadas a este proyecto.");
            return;
        }

        System.out.println("\n--- Lista de Tareas del Proyecto: " + proyecto.getNombre() + " ---");
        for (int i = 0; i < proyecto.getTareas().size(); i++) {
            Tarea tarea = proyecto.getTareas().get(i);
            System.out.println((i + 1) + ". " + tarea.getNombre());
            System.out.println("   Descripcion: " + tarea.getDescripcion());
            System.out.println("   Prioridad: " + tarea.getPrioridad());
            System.out.println("   Estado: " + tarea.getEstado());
            System.out.println("   Fecha de vencimiento: " + tarea.getFechaVencimiento());
            System.out.println("-----------------------------------------");
        }
        System.out.println("Presione Enter para continuar.");
        scanner.nextLine();
    }
   private static void modificarTarea(Proyecto proyecto) {
        if (proyecto.getTareas().isEmpty()) {
            System.out.println("\nCrea una tarea primero");
            return;
        }
        System.out.print("\nIngresa el nombre de la tarea que quieres modificar (Enter para cancelar): ");
        String nombreTarea = scanner.nextLine();

        if (nombreTarea.isEmpty()) {
            System.out.println("Cancelado");
            return;
        }
        // Buscar la tarea por nombre
        Tarea tareaSeleccionada = null;
        for (Tarea tarea : proyecto.getTareas()) {
            if (tarea.getNombre().equalsIgnoreCase(nombreTarea)) {
                tareaSeleccionada = tarea;
                break;
            }
        }
        //Error en el nombre
        if (tareaSeleccionada == null) {
            System.out.println("Tarea no encontrada"
                    + "");
            return;
        }

        System.out.println("\nMODIFICAR TAREA " + tareaSeleccionada.getNombre());
        System.out.println("Si quieres conservar un dato como ya estaba tan solo presiona enter");

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) tareaSeleccionada.setNombre(nuevoNombre);

        System.out.print("Nueva descripcion: ");
        String nuevaDescripcion = scanner.nextLine();
        if (!nuevaDescripcion.isEmpty()) tareaSeleccionada.setDescripcion(nuevaDescripcion);

        System.out.print("Nueva prioridad: ");
        String nuevaPrioridad = scanner.nextLine();
        if (!nuevaPrioridad.isEmpty()) tareaSeleccionada.setPrioridad(nuevaPrioridad);

        System.out.print("Nuevo estado: ");
        String nuevoEstado = scanner.nextLine();
        if (!nuevoEstado.isEmpty()) tareaSeleccionada.setEstado(nuevoEstado);

        System.out.print("Nueva fecha de vencimiento: ");
        String nuevaFecha = scanner.nextLine();
        if (!nuevaFecha.isEmpty()) tareaSeleccionada.setFechaVencimiento(nuevaFecha);

        System.out.println("Tarea modificada exitosamente.");
}

    private static void eliminarTarea(Proyecto proyecto) {
        if (proyecto.getTareas().isEmpty()) {
            System.out.println("\nPrimero crea una tarea."); // En caso de que no haya tareas creadas
            return;
        }

        System.out.print("\nIngresa el nombre de la tarea que desea eliminar (Enter para cancelar): ");
        String nombreTarea = scanner.nextLine();

        if (nombreTarea.isEmpty()) {
            System.out.println("Operacion cancelada");
            return;
        }

        // Iterar hasta encontrar la tarea
        Tarea tareaAEliminar = null;
        for (Tarea tarea : proyecto.getTareas()) {
            if (tarea.getNombre().equalsIgnoreCase(nombreTarea)) {
                tareaAEliminar = tarea;
                break;
            }
        }
        // Error
        if (tareaAEliminar == null) {
            System.out.println("\nNo se encontro ninguna tarea con ese nombre.");
            return;
        }

        // Confirmar eliminación
        System.out.print("Seguro que desea eliminar esta tarea: '" + tareaAEliminar.getNombre() + "'? (si/no): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("si")) {
            proyecto.getTareas().remove(tareaAEliminar);
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("Eliminacion cancelada.");
        }
}
    // Metodo para eliminar Proyectos
    private static void eliminarProyecto(Proyecto proyecto) {
        System.out.print("Estas seguro de querer eliminar este proyecto? (si/no): "); // Confirmacion
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("si")) {
            proyectos.remove(proyecto); //Eliminar el proyecto de la matriz o Arraylist
            System.out.println("Proyecto eliminado con exito.");
        } else {
            System.out.println("Eliminacion cancelada.");
        }
    }
    
    private static Usuario buscarUsuarioPorCorreo(String correo) { // Método usado anteriormente para iterar en los usarios disponibles del sistema
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) return usuario;
        }
        return null;}
    
    // Metodo para busar proyectos cuando entras al menu de proyectos
    private static Proyecto buscarProyectoPorNombre(String nombre) {
        for (Proyecto proyecto : proyectos) {
        // Ignorar mayusculas o minusculas en el nombre del proyecto
        if (proyecto.getNombre().equalsIgnoreCase(nombre.trim())) {
            // Verificar si el usuario actual está en la lista de equipoAsignado
            if (proyecto.getEquipoAsignado().contains(usuarioActual.getNombre())) {
                return proyecto;
            } else { // Si no esta
                System.out.println("Acceso denegado. No está asignado a este proyecto.");
                return null;
            }
        }
    }

    // En caso de no encontrar un proyecto con el nombre indicado
    System.out.println("No se encontro un proyecto con ese nombre. Intenta de nuevo.");
    return null;
    }
    // Cuando una entrada en los Menus no sea numerica se invoca este método
    private static int obtenerEntradaNumerica() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero. ");
            }
        }
    }
}