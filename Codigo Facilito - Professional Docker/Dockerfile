# ==========================================
# Dockerfile Genérico (Plantilla Base)
# ==========================================

# 1. Definir la imagen base (El SO y entorno)
# -> Para Node: node:18-alpine
# -> Para Python: python:3.10-slim
# -> Para Java: eclipse-temurin:17-jre-alpine
FROM node:18-alpine

# 2. Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# 3. Copiar ÚNICAMENTE los archivos de dependencias primero
# Esto optimiza enormemente la caché. Si el código cambia pero las 
# dependencias no, Docker se salta este paso y arranca más rápido.
# -> Para Node: COPY package*.json ./
# -> Para Python: COPY requirements.txt ./
COPY package*.json ./

# 4. Instalar las dependencias
# -> Para Node: RUN npm install --omit=dev
# -> Para Python: RUN pip install --no-cache-dir -r requirements.txt
RUN npm install

# 5. Copiar el resto del código fuente de la aplicación
# (Lo que esté en el .dockerignore será ignorado)
COPY . .

# 6. Documentar el puerto que la aplicación necesita para funcionar
EXPOSE 3000

# 7. Definir el comando por defecto para arrancar el servicio
# ENTRYPOINT define el binario principal (no se puede sobrescribir fácilmente)
# CMD define los argumentos por defecto (se pueden sobrescribir al hacer 'docker run')
ENTRYPOINT ["node", "server.js"]
CMD ["--port", "3000", "--mode", "production"]