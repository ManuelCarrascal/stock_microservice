package com.emazon.stock.ports.application.http.util;

public class RolePermissionConstants {
    public static final String ADMIN_ROLE = "hasRole('ADMIN')";
    public static final String AUX_BODEGA_ROLE = "hasRole('AUX_BODEGA')";
    public static final String CLIENTE_ROLE = "hasRole('CLIENTE')";

    private RolePermissionConstants() {
    }
}
