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

        CargoRepositorio cargoRepo = new CargoRepositorioJPA(emf);

        Cargo nuevoCargo = new Cargo("DEV", "Desarrollador");
        nuevoCargo.setDescripcion("Encargado de desarrollo de software");
        Cargo creadoCargo = cargoRepo.crear(nuevoCargo);
        System.out.println("Cargo creado: " + creadoCargo);

        Cargo encontradoCargo = cargoRepo.buscarPorId(creadoCargo.getId()).orElse(null);
        System.out.println("Encontrado por ID: " + encontradoCargo);

        Cargo porCodigoCargo = cargoRepo.buscarPorCodigo("DEV").orElse(null);
        System.out.println("Encontrado por código: " + porCodigoCargo);

        System.out.println("Todos los cargos:");
        for (Cargo c : cargoRepo.listarTodos()) {
            System.out.println(" - " + c.getCodigo() + ": " + c.getNombre());
        }

        EmpleadoRepositorio empleadoRepo = new EmpleadoRepositorioJPA(emf);

        Empleado emp = new Empleado("E001", "Ana López", "ana.lopez@empresa.com", creadoCargo);
        Empleado creadoEmp = empleadoRepo.crear(emp);
        System.out.println("Empleado creado: " + creadoEmp);

        Empleado encontradoEmp = empleadoRepo.buscarPorCodigo("E001").orElse(null);
        System.out.println("Encontrado por código: " + encontradoEmp);

        System.out.println("Todos los empleados:");
        for (Empleado e : empleadoRepo.listarTodos()) {
            System.out.println(" - " + e.getCodigo() + ": " + e.getNombre() + " (" + e.getCargo().getNombre() + ")");
        }

        emf.close();
    }
}
