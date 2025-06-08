import 'package:flutter/material.dart';
import 'package:frontend/services/api_service.dart';
import 'package:frontend/models/user.dart';
import 'admin_login_page.dart';

class UserRegisterPage extends StatefulWidget {
  @override
  _UserRegisterPageState createState() => _UserRegisterPageState();
}

class _UserRegisterPageState extends State<UserRegisterPage> {
  final _formKey = GlobalKey<FormState>();
  final ApiService _apiService = ApiService();

  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _nombreController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();

  String _rolSeleccionado = 'estudiante';
  bool _isRegistering = false;
  String? _errorMessage;

  final List<String> roles = [
    'estudiante',
    'docente',
    'proveedor de servicios',
    'vigilancia',
  ];

  void _register() async {
    if (!_formKey.currentState!.validate()) return;

    setState(() {
      _isRegistering = true;
      _errorMessage = null;
    });

    try {
      final nuevoUsuario = User(
        username: _usernameController.text.trim(),
        password: _passwordController.text,
        nombreCompleto: _nombreController.text.trim(),
        email: _emailController.text.trim(),
        rol: _rolSeleccionado,
      );

      await _apiService.crearUsuario(nuevoUsuario);
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Usuario registrado exitosamente.')),
      );
      Navigator.pop(context);
    } catch (e) {
      setState(() {
        _errorMessage = 'Error al registrar usuario: $e';
      });
    } finally {
      setState(() {
        _isRegistering = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Registro Usuario'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(24.0),
        child: SingleChildScrollView(
          child: Form(
            key: _formKey,
            child: Column(
              children: [
                TextFormField(
                  controller: _usernameController,
                  decoration: InputDecoration(labelText: 'Usuario'),
                  validator: (value) =>
                      value == null || value.isEmpty ? 'Campo requerido' : null,
                ),
                SizedBox(height: 16),
                TextFormField(
                  controller: _passwordController,
                  decoration: InputDecoration(labelText: 'Contraseña'),
                  obscureText: true,
                  validator: (value) =>
                      value == null || value.isEmpty ? 'Campo requerido' : null,
                ),
                SizedBox(height: 16),
                TextFormField(
                  controller: _nombreController,
                  decoration: InputDecoration(labelText: 'Nombre Completo'),
                  validator: (value) =>
                      value == null || value.isEmpty ? 'Campo requerido' : null,
                ),
                SizedBox(height: 16),
                TextFormField(
                  controller: _emailController,
                  decoration: InputDecoration(labelText: 'Email'),
                  validator: (value) =>
                      value == null || value.isEmpty ? 'Campo requerido' : null,
                ),
                SizedBox(height: 16),
                DropdownButtonFormField<String>(
                  value: _rolSeleccionado,
                  items: roles
                      .map((rol) => DropdownMenuItem(
                            value: rol,
                            child: Text(rol[0].toUpperCase() + rol.substring(1)),
                          ))
                      .toList(),
                  onChanged: (valor) {
                    setState(() {
                      _rolSeleccionado = valor!;
                    });
                  },
                  decoration: InputDecoration(labelText: 'Rol'),
                  validator: (value) =>
                      value == null || value.isEmpty ? 'Seleccione un rol' : null,
                ),
                SizedBox(height: 24),
                _isRegistering
                    ? CircularProgressIndicator()
                    : ElevatedButton(
                        onPressed: _register,
                        child: Text('Registrarse'),
                      ),
                if (_errorMessage != null) ...[
                  SizedBox(height: 16),
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