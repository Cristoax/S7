package com.empresix.gestion;

import com.empresix.gestion.entity.Cargo;
import com.empresix.gestion.entity.Empleado;
import com.empresix.gestion.repositorio.CargoRepositorio;
import com.empresix.gestion.repositorio.CargoRepositorioJPA;
import com.empresix.gestion.repositorio.EmpleadoRepositorio;
import com.empresix.gestion.repositorio.EmpleadoRepositorioJPA;

import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionEC");

        // Repositorio de cargos
        CargoRepositorio cargoRepo = new CargoRepositorioJPA(emf);

        // Crear un nuevo cargo
        Cargo nuevoCargo = new Cargo("DEV", "Desarrollador");
        nuevoCargo.setDescripcion("Encargado de desarrollo de software");
        Cargo creadoCargo = cargoRepo.crear(nuevoCargo);
        System.out.println("Cargo creado: " + creadoCargo);

        // Buscar cargo por ID
        Cargo encontradoCargo = cargoRepo.buscarPorId(creadoCargo.getId()).orElse(null);
        System.out.println("Encontrado por ID: " + encontradoCargo);

        // Buscar cargo por código
        Cargo porCodigoCargo = cargoRepo.buscarPorCodigo("DEV").orElse(null);
        System.out.println("Encontrado por código: " + porCodigoCargo);

        // Listar todos los cargos
        System.out.println("Todos los cargos:");
        for (Cargo c : cargoRepo.listarTodos()) {
            System.out.println(" - " + c.getCodigo() + ": " + c.getNombre());
        }

        // Repositorio de empleados
        EmpleadoRepositorio empleadoRepo = new EmpleadoRepositorioJPA(emf);

        // Crear un empleado asociado al cargo creado
        Empleado emp = new Empleado("E001", "Ana López", "ana.lopez@empresa.com", creadoCargo);
        Empleado creadoEmp = empleadoRepo.crear(emp);
        System.out.println("Empleado creado: " + creadoEmp);

        // Buscar empleado por código
        Empleado encontradoEmp = empleadoRepo.buscarPorCodigo("E001").orElse(null);
        System.out.println("Encontrado por código: " + encontradoEmp);

        // Listar todos los empleados
        System.out.println("Todos los empleados:");
        for (Empleado e : empleadoRepo.listarTodos()) {
            System.out.println(" - " + e.getCodigo() + ": " + e.getNombre() + " (" + e.getCargo().getNombre() + ")");
        }

        emf.close();
    }
}
