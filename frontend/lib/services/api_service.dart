/*import 'dart:convert';
import 'package:http/http.dart' as http;
import '../models/usuario.dart';

class ApiService {
  // Cambiar localhost por 10.0.2.2 para el emulador Android
  static const String baseUrl = 'http://10.0.2.2:8080/api/usuarios';

  Future<List<Usuario>> getUsuarios() async {
    final response = await http.get(Uri.parse(baseUrl));
    if (response.statusCode == 200) {
      final List<dynamic> listaJson = json.decode(response.body);
      return listaJson.map((json) => Usuario.fromJson(json)).toList();
    } else {
      throw Exception('Error al obtener usuarios: ${response.statusCode}');
    }
  }

  Future<Usuario> crearUsuario(Usuario usuario) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(usuario.toJson()),
    );
    if (response.statusCode == 200 || response.statusCode == 201) {
      return Usuario.fromJson(json.decode(response.body));
    } else {
      throw Exception('Error al crear usuario: ${response.statusCode} ${response.body}');
    }
  }

  Future<void> eliminarUsuario(int id) async {
    final response = await http.delete(Uri.parse('$baseUrl/$id'));
    if (response.statusCode != 204) {
      throw Exception('Error al eliminar usuario: ${response.statusCode}');
    }
  }

  Future<Usuario> actualizarUsuario(Usuario usuario) async {
    final response = await http.put(
      Uri.parse('$baseUrl/${usuario.id}'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(usuario.toJson()),
    );
    if (response.statusCode == 200) {
      return Usuario.fromJson(json.decode(response.body));
    } else {
      throw Exception('Error al actualizar usuario: ${response.statusCode}');
    }
  }
}*/