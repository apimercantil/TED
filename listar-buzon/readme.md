# Transmisión Electrónica de Datos(TED) - Listar Buzón

[Más informacion sobre el API](https://apiportal.mercantilbanco.com/mercantil-banco/produccion/product/24422)

### Índice
    
1. [Ejemplo de encriptación](./src/com/bancomercantil/crypto/AES.java)
2. [Ejemplo de petición](./src/com/bancomercantil/app/App.java)
3. [Ejemplo práctico](#playground)<br>
3.1 [Configuración y uso del ejemplo](#example-config)<br>
3.2 [Paso a paso de una ejecución simple](#example)<br>
4. [Dependencias](#dependecies)
5. [Request y Response](#rq)
6. [Códigos de error](#error-codes)

<a id="playground" ></a>
## Ejemplo práctico

<a id="example-config"> </a>
### Configuración y uso del ejemplo práctico

1. Descargar el código ubicado en este repositorio en su ordenador [pulsando aquí](https://github.com/apimercantil/TED/releases/download/V1/listar-buzon.zip).<br>

2. Descomprimir el archivo descargado en su ordenador.<br>

3. Abrir con un IDE de su preferencia(NetBeans, Eclipse) el archivo **App.java** ubicado en la carpeta app del ejemplo.<br>

4. Configurar sus credenciales en el archivo **App.java** (Utilizar las credenciales suministradas por Mercantil Banco).<br>

5. Configurar las [dependencias](#dependecies) del proyecto. (Puede usar cualquier herramienta de automatización para el manejo de las dependencias si así lo desea.)<br>
    5.1. [Gson 2.9.0](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.9.0)<br>

6. Utilizar los archivos con la estructura suministrada por Mercantil Banco para ejecutar las pruebas del API TED.<br>

<a id="example"> </a>
### Ejecución simple con Eclipse IDE Y Java 8

1. Crear un nuevo proyecto Java desde el Eclipse IDE.<br>
![Imagen de ejemplo](./img/readme-img-1.png)<br>
![Imagen de ejemplo](./img/readme-img-2.png)<br>

2. Descargar el código ubicado en este repositorio en su ordenador [pulsando aquí](https://github.com/apimercantil/TED/releases/download/V1/listar-buzon.zip).<br>

3. Descomprimir el archivo descargado en su ordenador.<br>

4. Copiar la carpeta **com** ubicada en la carpeta **src** del archivo descomprimido previamente.<br>

5. Pegar la carpeta en el proyecto creado en pasos anteriores.<br>
![Imagen de ejemplo](./img/readme-img-3.png)<br>

6. Descargar las [dependencias](#dependecies) del proyecto (En este caso se guardan en una carpeta nueva **lib**).<br>
![Imagen de ejemplo](./img/readme-img-4.png)<br>

7. Configurar las [dependencias](#dependecies) del proyecto.<br>
![Imagen de ejemplo](./img/readme-img-5.png)<br>
![Imagen de ejemplo](./img/readme-img-6.png)<br>

8. Configurar las credenciales suministradas por Mercantil Banco en el archivo  **App.java** ubicado en el paquete **com.bancomercantil.app** en la carpeta **src** del proyecto.<br>
![Imagen de ejemplo](./img/readme-img-7.png)<br>

9. Ejecutar el proyecto.<br>
![Imagen de ejemplo](./img/readme-img-8.png)<br>

## Información sobre el API y el ejemplo

<a id="dependecies"></a>
### Dependencias
1. [Gson 2.9.0](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.9.0) (JSON Serializer)<br>

<a id="rq"></a>
### Request y Reponse

![Request example](https://www.mercantilbanco.com/mercprod/apiportal/images/ted-listar-buzon-request-json.png)<br>
![Response example](https://www.mercantilbanco.com/mercprod/apiportal/images/ted-listar-buzon-response-json.png)<br>

Para información detallada sobre los campos del request pulsa [aquí](https://www.mercantilbanco.com/mercprod/apiportal/pdfs/api_ted_descripcion_de_atributos_y_campos_cargar_archivo_1.pdf)

<a id="error-codes"></a>
### Códigos de error

Para información detallada sobre la lista de errores pulsa [aquí](https://www.mercantilbanco.com/mercprod/apiportal/pdfs/api_ted_tipo_de_errores_1.pdf).
