class User {
  int? id;
  String username;
  String password;
  String nombreCompleto;
  String email;
  String rol;

  User({
    this.id,
    required this.username,
    required this.password,
    required this.nombreCompleto,
    required this.email,
    required this.rol,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'] != null ? json['id'] as int : null,
      username: json['username'] as String,
      password: json['password'] as String,
      nombreCompleto: json['nombreCompleto'] as String,
      email: json['email'] as String,
      rol: json['rol'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    final map = {
      'username': username,
      'password': password,
      'nombreCompleto': nombreCompleto,
      'email': email,
      'rol': rol,
    };

    if (id != null) {
  map['id'] = id!.toString();
}


    return map;
  }
}
