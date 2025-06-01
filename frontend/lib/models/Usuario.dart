/*class Usuario {
  final int? id;
  final String username;
  final String password;
  final String nombreCompleto;
  final String email;
  final String rol;

  Usuario({
    this.id,
    required this.username,
    required this.password,
    required this.nombreCompleto,
    required this.email,
    required this.rol,
  });

  factory Usuario.fromJson(Map<String, dynamic> json) {
  return Usuario(
    id: json['id'],
    username: json['username'],
    password: json['password'],  // Aquí asignas la contraseña para que puedas comparar en Flutter (solo para pruebas)
    nombreCompleto: json['nombreCompleto'],
    email: json['email'],
    rol: json['rol'],
  );
}


  Map<String, dynamic> toJson() {
    return {
      if (id != null) 'id': id,
      'username': username,
      'password': password,
      'nombreCompleto': nombreCompleto,
      'email': email,
      'rol': rol,
    };
  }
}
*/