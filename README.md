clone Repo vào Intellij IDEA
Bước 1: nhấn SHIFT + ALT + CTRL + S
  1. Ở mục project, chọn SDK -> download SDK -> chọn version 21 và vendor: Oracle OpenJDK
  2. Language Level: 21
  3. Ở mục Module, Sources -> language level: 21 record patterns...
  4. Dependencies -> Module SDK -> chọn Project SDK ms-21
  5. OK
Bước 2:
  1. Edit Configurations
  2. add new Application -> Name: Main (hoặc) springboot_client
  3. Build and run: chọn Microsoft OpenJDK 21.0.10
  4. kế bên điền: org.example.Main
Bước 3: update lại dependencies trong pom.xml
