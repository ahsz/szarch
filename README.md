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
  - int add(User)

