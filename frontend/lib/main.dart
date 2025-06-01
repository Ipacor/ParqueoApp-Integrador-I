/*import 'package:flutter/material.dart';
import 'models/usuario.dart';
import 'services/api_service.dart';

void main() {
  runApp(ParqueoApp());
}

class ParqueoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ParqueoApp',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: LoginScreen(),
    );
  }
}

// Pantalla Login
class LoginScreen extends StatefulWidget {
  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final ApiService _apiService = ApiService();

  bool _isLoading = false;
  String? _errorMessage;

  @override
  void initState() {
    super.initState();
    _verificarUsuarios();
  }

  void _verificarUsuarios() async {
    final usuarios = await _apiService.getUsuarios();
    if (usuarios.isEmpty) {
      Future.delayed(Duration.zero, () => _mostrarFormularioRegistroInicial());
    }
  }

  void _mostrarFormularioRegistroInicial() {
    final _formKey = GlobalKey<FormState>();
    final _usernameController = TextEditingController();
    final _passwordController = TextEditingController();
    final _nombreController = TextEditingController();
    final _emailController = TextEditingController();
    String _rolSeleccionado = 'ADMIN';

    showDialog(
      context: context,
      barrierDismissible: false,
      builder: (context) {
        return WillPopScope(
          onWillPop: () async {
            return false;
          },
          child: AlertDialog(
            title: Text('Registro Inicial (Administrador)'),
            content: Form(
              key: _formKey,
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    TextFormField(
                      controller: _usernameController,
                      decoration: InputDecoration(labelText: 'Usuario'),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Campo requerido' : null,
                    ),
                    TextFormField(
                      controller: _passwordController,
                      decoration: InputDecoration(labelText: 'Contraseña'),
                      obscureText: true,
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Campo requerido' : null,
                    ),
                    TextFormField(
                      controller: _nombreController,
                      decoration: InputDecoration(labelText: 'Nombre Completo'),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Campo requerido' : null,
                    ),
                    TextFormField(
                      controller: _emailController,
                      decoration: InputDecoration(labelText: 'Email'),
                      validator: (value) =>
                          value == null || value.isEmpty ? 'Campo requerido' : null,
                    ),
                  ],
                ),
              ),
            ),
            actions: [
              ElevatedButton(
                onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    try {
                      final nuevoUsuario = Usuario(
                        username: _usernameController.text.trim(),
                        password: _passwordController.text,
                        nombreCompleto: _nombreController.text.trim(),
                        email: _emailController.text.trim(),
                        rol: _rolSeleccionado,
                      );
                      await _apiService.crearUsuario(nuevoUsuario);
                      Navigator.of(context).pop();
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Usuario creado. Ahora inicia sesión.')),
                      );
                    } catch (e) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Error al crear usuario: $e')),
                      );
                    }
                  }
                },
                child: Text('Registrar'),
              ),
            ],
          ),
        );
      },
    );
  }

  void _login() async {
    final username = _usernameController.text.trim();
    final password = _passwordController.text;

    if (username.isEmpty || password.isEmpty) {
      setState(() {
        _errorMessage = 'Por favor ingresa usuario y contraseña';
      });
      return;
    }

    setState(() {
      _isLoading = true;
      _errorMessage = null;
    });

    try {
      final usuarios = await _apiService.getUsuarios();
      final usuarioEncontrado = usuarios.firstWhere(
        (u) => u.username == username,
        orElse: () => Usuario(
          id: null,
          username: '',
          password: '',
          nombreCompleto: '',
          email: '',
          rol: '',
        ),
      );

      if (usuarioEncontrado.username == '') {
        setState(() {
          _errorMessage = 'Usuario no encontrado';
          _isLoading = false;
        });
        return;
      }

      if (password != usuarioEncontrado.password) {
        setState(() {
          _errorMessage = 'Contraseña incorrecta';
          _isLoading = false;
        });
        return;
      }

      if (usuarioEncontrado.rol.toUpperCase() == 'ADMIN') {
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(
              builder: (context) => AdminScreen(usuarioActual: usuarioEncontrado)),
        );
      } else {
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (context) => UsuarioScreen()),
        );
      }
    } catch (e) {
      setState(() {
        _errorMessage = 'Error de conexión: $e';
        _isLoading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Login')),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Center(
          child: SingleChildScrollView(
            child: Column(
              children: [
                TextField(
                  controller: _usernameController,
                  decoration: InputDecoration(labelText: 'Usuario'),
                ),
                SizedBox(height: 16),
                TextField(
                  controller: _passwordController,
                  obscureText: true,
                  decoration: InputDecoration(labelText: 'Contraseña'),
                ),
                SizedBox(height: 24),
                _isLoading
                    ? CircularProgressIndicator()
                    : ElevatedButton(
                        onPressed: _login,
                        child: Text('Ingresar'),
                      ),
                if (_errorMessage != null) ...[
                  SizedBox(height: 20),
                  Text(
                    _errorMessage!,
                    style: TextStyle(color: Colors.red),
                  ),
                ],
              ],
            ),
          ),
        ),
      ),
    );
  }
}

// Pantalla Admin - CRUD COMPLETO
class AdminScreen extends StatefulWidget {
  final Usuario usuarioActual;

  AdminScreen({required this.usuarioActual});

  @override
  _AdminScreenState createState() => _AdminScreenState();
}

class _AdminScreenState extends State<AdminScreen> {
  final ApiService apiService = ApiService();
  late Future<List<Usuario>> usuariosFuture;

  final _formKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _nombreController = TextEditingController();
  final _emailController = TextEditingController();
  String _rolSeleccionado = 'USUARIO';

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
    _rolSeleccionado = 'USUARIO';
  }

  void _logout() {
    Navigator.pushAndRemoveUntil(
      context,
      MaterialPageRoute(builder: (context) => LoginScreen()),
      (route) => false,
    );
  }

  // CREATE - Crear nuevo usuario
  void _mostrarFormularioCrearUsuario() {
    _clearForm(); // Limpiar formulario antes de mostrar
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
                        items: ['ADMIN', 'USUARIO']
                            .map((rol) => DropdownMenuItem(
                                  value: rol,
                                  child: Text(rol),
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
                        final nuevoUsuario = Usuario(
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

  // UPDATE - Editar usuario existente
  void _editarUsuario(Usuario usuario) {
    _usernameController.text = usuario.username;
    _passwordController.text = usuario.password;
    _nombreController.text = usuario.nombreCompleto;
    _emailController.text = usuario.email;
    _rolSeleccionado = usuario.rol;

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
                        items: ['ADMIN', 'USUARIO']
                            .map((rol) => DropdownMenuItem(
                                  value: rol,
                                  child: Text(rol),
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
                        final usuarioActualizado = Usuario(
                          id: usuario.id,
                          username: _usernameController.text.trim(),
                          password: _passwordController.text,
                          nombreCompleto: _nombreController.text.trim(),
                          email: _emailController.text.trim(),
                          rol: _rolSeleccionado,
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

  // DELETE - Eliminar usuario
  void _eliminarUsuario(Usuario usuario) async {
    // Confirmación antes de eliminar
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

  // Ver detalles del usuario
  void _verDetallesUsuario(Usuario usuario) {
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
        title: Text('Admin - ${widget.usuarioActual.nombreCompleto}'),
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
      body: Column(
        children: [
          // Header con información
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
          // Lista de usuarios
          Expanded(
            child: FutureBuilder<List<Usuario>>(
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
                    return Card(
                      margin: EdgeInsets.symmetric(vertical: 4, horizontal: 8),
                      child: ListTile(
                        leading: CircleAvatar(
                          backgroundColor: usuario.rol == 'ADMIN' 
                              ? Colors.red.shade100 
                              : Colors.blue.shade100,
                          child: Icon(
                            usuario.rol == 'ADMIN' 
                                ? Icons.admin_panel_settings 
                                : Icons.person,
                            color: usuario.rol == 'ADMIN' 
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
                                color: usuario.rol == 'ADMIN' 
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
                                _eliminarUsuario(usuario);
                                break;
                            }
                          },
                          itemBuilder: (context) => [
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
                          ],
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

// Pantalla Usuario común
class UsuarioScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Usuario'),
        actions: [
          IconButton(
            onPressed: () {
              Navigator.pushAndRemoveUntil(
                context,
                MaterialPageRoute(builder: (context) => LoginScreen()),
                (route) => false,
              );
            },
            icon: Icon(Icons.logout),
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.local_parking, size: 100, color: Colors.blue),
            SizedBox(height: 20),
            Text(
              'Bienvenido Usuario',
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10),
            Text(
              'Pantalla para usuario común',
              style: TextStyle(fontSize: 16, color: Colors.grey),
            ),
          ],
        ),
      ),
    );
  }
}*/