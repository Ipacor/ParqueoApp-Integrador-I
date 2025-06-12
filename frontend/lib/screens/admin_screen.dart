import 'package:flutter/material.dart';
import 'package:frontend/services/api_service.dart';
import 'package:frontend/models/user.dart';
import 'admin_login_page.dart';

class AdminScreen extends StatefulWidget {
  final User usuarioActual;

  AdminScreen({required this.usuarioActual});

  @override
  _AdminScreenState createState() => _AdminScreenState();
}

class _AdminScreenState extends State<AdminScreen> {
  final ApiService apiService = ApiService();
  late Future<List<User>> usuariosFuture;

  final _formKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _nombreController = TextEditingController();
  final _emailController = TextEditingController();
  String _rolSeleccionado = 'estudiante';

  final List<String> roles = [
    'estudiante',
    'docente',
    'proveedor de servicios',
    'vigilancia',
  ];

  @override
  void initState() {
    super.initState();
    usuariosFuture = apiService.getUsuarios();
  }

  void _refreshUsuarios() {
    setState(() {
      usuariosFuture = apiService.getUsuarios();
    });
  }

  void _clearForm() {
    _usernameController.clear();
    _passwordController.clear();
    _nombreController.clear();
    _emailController.clear();
    _rolSeleccionado = roles[0];
  }

  void _logout() {
    Navigator.pushAndRemoveUntil(
      context,
      MaterialPageRoute(builder: (context) => AdminLoginPage()),
      (route) => false,
    );
  }

  void _mostrarFormularioCrearUsuario() {
    _clearForm();
    showDialog(
      context: context,
      builder: (context) {
        return StatefulBuilder(
          builder: (context, setDialogState) {
            return AlertDialog(
              title: Text('Crear Nuevo Usuario'),
              content: Form(
                key: _formKey,
                child: SingleChildScrollView(
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      TextFormField(
                        controller: _usernameController,
                        decoration: InputDecoration(
                          labelText: 'Usuario',
                          prefixIcon: Icon(Icons.person),
                        ),
                        validator: (value) =>
                            value == null || value.isEmpty ? 'Campo requerido' : null,
                      ),
                      SizedBox(height: 10),
                      TextFormField(
                        controller: _passwordController,
                        decoration: InputDecoration(
                          labelText: 'Contraseña',
                          prefixIcon: Icon(Icons.lock),
                        ),
                        obscureText: true,
                        validator: (value) =>
                            value == null || value.isEmpty ? 'Campo requerido' : null,
                      ),
                      SizedBox(height: 10),
                      TextFormField(
                        controller: _nombreController,
                        decoration: InputDecoration(
                          labelText: 'Nombre completo',
                          prefixIcon: Icon(Icons.badge),
                        ),
                        validator: (value) =>
                            value == null || value.isEmpty ? 'Campo requerido' : null,
                      ),
                      SizedBox(height: 10),
                      TextFormField(
                        controller: _emailController,
                        decoration: InputDecoration(
                          labelText: 'Email',
                          prefixIcon: Icon(Icons.email),
                        ),
                        keyboardType: TextInputType.emailAddress,
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Campo requerido';
                          }
                          if (!value.contains('@')) {
                            return 'Email inválido';
                          }
                          return null;
                        },
                      ),
                      SizedBox(height: 10),
                      DropdownButtonFormField<String>(
                        value: _rolSeleccionado,
                        items: roles
                            .map((rol) => DropdownMenuItem(
                                  value: rol,
                                  child: Text(rol[0].toUpperCase() + rol.substring(1)),
                                ))
                            .toList(),
                        onChanged: (valor) {
                          setDialogState(() {
                            _rolSeleccionado = valor!;
                          });
                        },
                        decoration: InputDecoration(
                          labelText: 'Rol',
                          prefixIcon: Icon(Icons.admin_panel_settings),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              actions: [
                TextButton(
                  onPressed: () {
                    Navigator.of(context).pop();
                    _clearForm();
                  },
                  child: Text('Cancelar'),
                ),
                ElevatedButton(
                  onPressed: () async {
                    if (_formKey.currentState!.validate()) {
                      try {
                        final nuevoUsuario = User(
                          username: _usernameController.text.trim(),
                          password: _passwordController.text,
                          nombreCompleto: _nombreController.text.trim(),
                          email: _emailController.text.trim(),
                          rol: _rolSeleccionado,
                        );
                        await apiService.crearUsuario(nuevoUsuario);
                        _refreshUsuarios();
                        Navigator.of(context).pop();
                        _clearForm();
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content: Text('Usuario creado exitosamente'),
                            backgroundColor: Colors.green,
                          ),
                        );
                      } catch (e) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content: Text('Error al crear usuario: $e'),
                            backgroundColor: Colors.red,
                          ),
                        );
                      }
                    }
                  },
                  child: Text('Guardar'),
                ),
              ],
            );
          },
        );
      },
    );
  }

  void _mostrarFormularioEditarUsuario(User usuario) {
    _usernameController.text = usuario.username;
    _passwordController.text = usuario.password;
    _nombreController.text = usuario.nombreCompleto;
    _emailController.text = usuario.email;

    final bool esAdminActual = usuario.id == widget.usuarioActual.id;

    // Preparación lista de roles y valor para DropdownButton
    String rolActualLower = usuario.rol.toLowerCase();
    List<String> currentRoles = List.from(roles);
    if (esAdminActual && rolActualLower != 'admin') {
      rolActualLower = 'admin';
      if (!currentRoles.contains('admin')) {
        currentRoles.add('admin');
      }
    } else if (!currentRoles.contains(rolActualLower)) {
      currentRoles.add(rolActualLower);
    }

    _rolSeleccionado = rolActualLower;

    showDialog(
      context: context,
      builder: (context) {
        return StatefulBuilder(
          builder: (context, setDialogState) {
            return AlertDialog(
              title: Text('Editar Usuario'),
              content: Form(
                key: _formKey,
                child: SingleChildScrollView(
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      TextFormField(
                        controller: _usernameController,
                        decoration: InputDecoration(
                          labelText: 'Usuario',
                          prefixIcon: Icon(Icons.person),
                        ),
                        validator: (value) =>
                            value == null || value.isEmpty ? 'Campo requerido' : null,
                      ),
                      SizedBox(height: 10),
                      TextFormField(
                        controller: _passwordController,
                        decoration: InputDecoration(
                          labelText: 'Contraseña',
                          prefixIcon: Icon(Icons.lock),
                        ),
                        obscureText: true,
                        validator: (value) =>
                            value == null || value.isEmpty ? 'Campo requerido' : null,
                      ),
                      SizedBox(height: 10),
                      TextFormField(
                        controller: _nombreController,
                        decoration: InputDecoration(
                          labelText: 'Nombre completo',
                          prefixIcon: Icon(Icons.badge),
                        ),
                        validator: (value) =>
                            value == null || value.isEmpty ? 'Campo requerido' : null,
                      ),
                      SizedBox(height: 10),
                      TextFormField(
                        controller: _emailController,
                        decoration: InputDecoration(
                          labelText: 'Email',
                          prefixIcon: Icon(Icons.email),
                        ),
                        keyboardType: TextInputType.emailAddress,
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Campo requerido';
                          }
                          if (!value.contains('@')) {
                            return 'Email inválido';
                          }
                          return null;
                        },
                      ),
                      SizedBox(height: 10),
                      DropdownButtonFormField<String>(
                        value: _rolSeleccionado,
                        items: currentRoles
                            .map((rol) => DropdownMenuItem(
                                  value: rol,
                                  child: Text(rol[0].toUpperCase() + rol.substring(1)),
                                ))
                            .toList(),
                        onChanged: esAdminActual
                            ? null
                            : (valor) {
                                setDialogState(() {
                                  _rolSeleccionado = valor!;
                                });
                              },
                        decoration: InputDecoration(
                          labelText: 'Rol',
                          prefixIcon: Icon(Icons.admin_panel_settings),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              actions: [
                TextButton(
                  onPressed: () {
                    Navigator.of(context).pop();
                    _clearForm();
                  },
                  child: Text('Cancelar'),
                ),
                ElevatedButton(
                  onPressed: () async {
                    if (_formKey.currentState!.validate()) {
                      try {
                        final usuarioActualizado = User(
                          id: usuario.id,
                          username: _usernameController.text.trim(),
                          password: _passwordController.text,
                          nombreCompleto: _nombreController.text.trim(),
                          email: _emailController.text.trim(),
                          rol: _rolSeleccionado.toUpperCase() == 'ADMIN'
                              ? 'ADMIN'
                              : _rolSeleccionado,
                        );
                        await apiService.actualizarUsuario(usuarioActualizado);
                        _refreshUsuarios();
                        Navigator.of(context).pop();
                        _clearForm();
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content: Text('Usuario actualizado exitosamente'),
                            backgroundColor: Colors.green,
                          ),
                        );
                      } catch (e) {
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content: Text('Error al actualizar usuario: $e'),
                            backgroundColor: Colors.red,
                          ),
                        );
                      }
                    }
                  },
                  child: Text('Actualizar'),
                ),
              ],
            );
          },
        );
      },
    );
  }

  void _editarUsuario(User usuario) {
    _mostrarFormularioEditarUsuario(usuario);
  }

  void _eliminarUsuario(User usuario) async {
    final bool esAdminActual = usuario.id == widget.usuarioActual.id;
    if (esAdminActual) return;

    final confirmar = await showDialog<bool>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Confirmar eliminación'),
          content: Text(
            '¿Estás seguro de que deseas eliminar al usuario "${usuario.nombreCompleto}"?\n\nEsta acción no se puede deshacer.',
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.of(context).pop(false),
              child: Text('Cancelar'),
            ),
            ElevatedButton(
              onPressed: () => Navigator.of(context).pop(true),
              style: ElevatedButton.styleFrom(backgroundColor: Colors.red),
              child: Text('Eliminar', style: TextStyle(color: Colors.white)),
            ),
          ],
        );
      },
    );

    if (confirmar == true) {
      try {
        await apiService.eliminarUsuario(usuario.id!);
        _refreshUsuarios();
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Usuario eliminado exitosamente'),
            backgroundColor: Colors.orange,
          ),
        );
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: Text('Error al eliminar usuario: $e'),
            backgroundColor: Colors.red,
          ),
        );
      }
    }
  }

  void _verDetallesUsuario(User usuario) {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: Text('Detalles del Usuario'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _buildDetailRow('ID:', usuario.id?.toString() ?? 'N/A'),
              _buildDetailRow('Usuario:', usuario.username),
              _buildDetailRow('Nombre:', usuario.nombreCompleto),
              _buildDetailRow('Email:', usuario.email),
              _buildDetailRow('Rol:', usuario.rol),
            ],
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: Text('Cerrar'),
            ),
          ],
        );
      },
    );
  }

  Widget _buildDetailRow(String label, String value) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4.0),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SizedBox(
            width: 80,
            child: Text(
              label,
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          Expanded(
            child: Text(value),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Administrador - ${widget.usuarioActual.nombreCompleto}'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
        actions: [
          IconButton(
            onPressed: _refreshUsuarios,
            icon: Icon(Icons.refresh),
            tooltip: 'Actualizar lista',
          ),
          IconButton(
            onPressed: _logout,
            icon: Icon(Icons.logout),
            tooltip: 'Cerrar sesión',
          ),
        ],
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              decoration: BoxDecoration(
                color: Colors.blue,
              ),
              child: Text(
                'Menú',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 24,
                ),
              ),
            ),
            ListTile(
              leading: Icon(Icons.home),
              title: Text('Inicio'),
              onTap: () {
                // Navegar a la pantalla de inicio
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.settings),
              title: Text('Configuraciones'),
              onTap: () {
                // Navegar a la pantalla de configuraciones
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: Icon(Icons.info),
              title: Text('Acerca de'),
              onTap: () {
                // Navegar a la pantalla de acerca de
                Navigator.pop(context);
              },
            ),
          ],
        ),
      ),
      body: Column(
        children: [
          Container(
            width: double.infinity,
            padding: EdgeInsets.all(16),
            color: Colors.blue.shade50,
            child: Text(
              'Gestión de Usuarios - CRUD Completo',
              style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.blue.shade800,
              ),
              textAlign: TextAlign.center,
            ),
          ),
          Expanded(
            child: FutureBuilder<List<User>>(
              future: usuariosFuture,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return Center(child: CircularProgressIndicator());
                }
                if (snapshot.hasError) {
                  return Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(Icons.error, size: 64, color: Colors.red),
                        SizedBox(height: 16),
                        Text('Error: ${snapshot.error}'),
                        SizedBox(height: 16),
                        ElevatedButton(
                          onPressed: _refreshUsuarios,
                          child: Text('Reintentar'),
                        ),
                      ],
                    ),
                  );
                }
                final usuarios = snapshot.data ?? [];

                if (usuarios.isEmpty) {
                  return Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Icon(Icons.people_outline, size: 64, color: Colors.grey),
                        SizedBox(height: 16),
                        Text(
                          'No hay usuarios registrados',
                          style: TextStyle(fontSize: 18, color: Colors.grey),
                        ),
                        SizedBox(height: 16),
                        ElevatedButton(
                          onPressed: _mostrarFormularioCrearUsuario,
                          child: Text('Crear primer usuario'),
                        ),
                      ],
                    ),
                  );
                }

                return ListView.builder(
                  padding: EdgeInsets.all(8),
                  itemCount: usuarios.length,
                  itemBuilder: (context, index) {
                    final usuario = usuarios[index];
                    final bool esAdminActual = usuario.id == widget.usuarioActual.id;

                    return Card(
                      margin: EdgeInsets.symmetric(vertical: 4, horizontal: 8),
                      child: ListTile(
                        leading: CircleAvatar(
                          backgroundColor: usuario.rol.toUpperCase() == 'ADMIN'
                              ? Colors.red.shade100
                              : Colors.blue.shade100,
                          child: Icon(
                            usuario.rol.toUpperCase() == 'ADMIN'
                                ? Icons.admin_panel_settings
                                : Icons.person,
                            color: usuario.rol.toUpperCase() == 'ADMIN'
                                ? Colors.red.shade700
                                : Colors.blue.shade700,
                          ),
                        ),
                        title: Text(
                          usuario.username,
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                        subtitle: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(usuario.nombreCompleto),
                            Text(
                              usuario.rol,
                              style: TextStyle(
                                color: usuario.rol.toUpperCase() == 'ADMIN'
                                    ? Colors.red
                                    : Colors.blue,
                                fontWeight: FontWeight.w500,
                              ),
                            ),
                          ],
                        ),
                        trailing: PopupMenuButton<String>(
                          onSelected: (value) {
                            switch (value) {
                              case 'ver':
                                _verDetallesUsuario(usuario);
                                break;
                              case 'editar':
                                _editarUsuario(usuario);
                                break;
                              case 'eliminar':
                                if (!esAdminActual) {
                                  _eliminarUsuario(usuario);
                                }
                                break;
                            }
                          },
                          itemBuilder: (context) {
                            List<PopupMenuEntry<String>> items = [
                              PopupMenuItem(
                                value: 'ver',
                                child: Row(
                                  children: [
                                    Icon(Icons.visibility, color: Colors.blue),
                                    SizedBox(width: 8),
                                    Text('Ver detalles'),
                                  ],
                                ),
                              ),
                              PopupMenuItem(
                                value: 'editar',
                                child: Row(
                                  children: [
                                    Icon(Icons.edit, color: Colors.orange),
                                    SizedBox(width: 8),
                                    Text('Editar'),
                                  ],
                                ),
                              ),
                            ];
                            if (!esAdminActual) {
                              items.add(
                                PopupMenuItem(
                                  value: 'eliminar',
                                  child: Row(
                                    children: [
                                      Icon(Icons.delete, color: Colors.red),
                                      SizedBox(width: 8),
                                      Text('Eliminar'),
                                    ],
                                  ),
                                ),
                              );
                            }
                            return items;
                          },
                        ),
                        onTap: () => _verDetallesUsuario(usuario),
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: _mostrarFormularioCrearUsuario,
        icon: Icon(Icons.add),
        label: Text('Nuevo Usuario'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
      ),
    );
  }
}
