/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Helpers;

/**
 *
 * @author fermin
 */
public class MessageHelper {
    public static String successMessage(String entityName, String action) {
        return "El " + entityName + " se ha " + action + " exitosamente.";
    }

    public static String errorMessage(String entityName, String action) {
        return "Error al " + action + " el " + entityName + ".";
    }
}
