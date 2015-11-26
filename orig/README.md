# szarch

-----------------
Utility interface
-----------------

  Variables:
  - connect (for set up DB connection)
  - conn (for storage DB connection)

  Methods:
  - int add(T object)
  - List<T> get(T object)
  - void delete(T object)
  - void update(T object)

-------------------------------------------------------------------------------------------------------------------------------

--------------------
User extends Utility
--------------------
  
Variables:
- id
- name
- password
- role_id
- aes_key for DB encrypt/decrypt (encryption included at source code)

Methods:
- int add(User user)
  - add a new user to the DB
  - id must be null!
  - name and password and role_id must be not null

- List<User> get(User user)
  - get user(s) from the DB
  - password must be null! 

- void delete(User user)
  - delete user from the DB
  - password must be null!
  - if all parameter is null, all user will be deleted!

- void update(User user)
  - update the user right to the id
  - id must be not null!
  - at least one of the other parameters (name, password, role_id) must be not null!

