# Guía Esencial de Git: De Cero a Profesional

Git es el sistema de control de versiones distribuido más utilizado del mundo. Permite registrar los cambios en tus archivos a lo largo del tiempo, colaborar con otros desarrolladores y volver a versiones anteriores si algo sale mal.

---

## 1. Instalación
La instalación varía según tu sistema operativo:

*   **Windows:** Descarga el instalador oficial desde [git-scm.com](https://git-scm.com/) y sigue el asistente (Git Bash incluido).
*   **macOS:** Abre la terminal y ejecuta `git --version`. Si no lo tienes, el sistema te pedirá instalar las herramientas de línea de comandos de Xcode. Alternativamente, usa Homebrew: `brew install git`.
*   **Linux (Debian/Ubuntu):** Ejecuta en la terminal: `sudo apt update && sudo apt install git`.

---

## 2. Configuración Inicial
Antes de hacer tu primer guardado (commit), Git necesita saber quién eres para firmar los cambios. Abre tu terminal y ejecuta:

```bash
# Configura tu nombre
git config --global user.name "Tu Nombre"

# Configura tu correo electrónico
git config --global user.email "tu@correo.com"

# Verifica tu configuración
git config --list

## 3. Inicializar un Proyecto

Para que Git empiece a rastrear un proyecto, debes inicializarlo dentro de la carpeta raíz de tu código.

```bash
# Navega a la carpeta de tu proyecto
cd ruta/a/tu/proyecto

# Inicializa el repositorio
git init
```

> **Nota:** Esto crea una carpeta oculta llamada `.git`. Aquí es donde Git guarda todo el historial y metadatos. No la borres.

---

## 4. Conceptos Clave: Staging Area y Stash Area

Para dominar Git, debes entender dónde "viven" tus archivos en un momento dado.

### El Staging Area (Área de Preparación)

Imagina que estás organizando una mudanza. El **Staging Area** es como la zona del pasillo donde vas apilando las cajas antes de subirlas al camión (el *commit*).

* **Working Directory:** Cuando modificas un archivo en tu editor, el cambio está en tu Directorio de Trabajo.
* **Control manual:** Git no guarda automáticamente los archivos modificados. Debes decirle explícitamente qué archivos quieres incluir en el próximo guardado pasándolos al Staging Area.
* **Precisión:** Esto te permite hacer un guardado preciso (ej. guardar solo los cambios del archivo A, y dejar los del archivo B para después).

### El Stash Area (El Cajón de Sastre Temporal)

Imagina que estás a la mitad de programar una función compleja y, de repente, tu jefe te pide arreglar un error urgente en otra parte del código. No puedes hacer *commit* porque la función está a medias, pero si cambias de rama, podrías perder o mezclar tus cambios.

* **Función:** El **Stash** te permite "esconder" o guardar temporalmente esos cambios incompletos en un cajón seguro sin hacer un commit.
* **Limpieza:** Tu directorio de trabajo vuelve a estar limpio (como en el último commit).
* **Flujo:** Puedes ir a arreglar el error, hacer el commit y luego "sacar" tus cambios incompletos del cajón para seguir trabajando exactamente donde lo dejaste.

---

## 5. Comandos Útiles y Necesarios

### Flujo de Trabajo Básico (Día a Día)

* `git status`: Tu mejor amigo. Te dice qué archivos han sido modificados, cuáles están en el Staging Area y en qué rama estás.
* `git add <archivo>`: Mueve un archivo específico al Staging Area.
* `git add .`: (Muy usado) Mueve todos los archivos modificados de la carpeta actual al Staging Area.
* `git commit -m "Mensaje descriptivo"`: Toma una "foto" definitiva de los archivos que están en el Staging Area y los guarda en el historial con un mensaje.
* `git log`: Muestra el historial de commits (quién, cuándo y qué se guardó).

### Trabajando con el Stash

* `git stash`: Guarda temporalmente tus cambios no "commiteados" y limpia tu área de trabajo.
* `git stash list`: Muestra la lista de todos los cambios que tienes guardados en el Stash.
* `git stash pop`: Recupera el último cambio guardado en el Stash y lo aplica a tu directorio de trabajo actual (y lo borra del Stash).
* `git stash drop`: Elimina el último elemento guardado en el Stash sin aplicarlo.

### Ramas (Branches)

Las ramas te permiten trabajar en nuevas características sin afectar el código principal.

* `git branch`: Lista todas las ramas locales. La rama actual tiene un asterisco `*`.
* `git branch <nombre_rama>`: Crea una nueva rama.
* `git switch <nombre_rama>`: *(O `git checkout <nombre_rama>`)* Cambia a otra rama existente.
* `git switch -c <nombre_rama>`: Crea una nueva rama y cambia a ella inmediatamente.
* `git merge <nombre_rama>`: Estando en la rama principal (ej. `main`), este comando fusiona los cambios de la `<nombre_rama>` en tu rama actual.

### Repositorios Remotos (GitHub, GitLab, Bitbucket)

* `git clone <url>`: Descarga un repositorio de internet a tu computadora local.
* `git remote add origin <url>`: Conecta tu repositorio local con un repositorio remoto vacío (usualmente llamado `origin`).
* `git push -u origin <nombre_rama>`: Sube tus commits locales al repositorio remoto.
* `git pull`: Descarga e integra los últimos cambios del repositorio remoto a tu rama local actual.

## 6. Estrategias de Merge a Nivel Profesional

Integrar el trabajo de distintas ramas es una tarea diaria. A nivel profesional, no siempre se usa el mismo tipo de *merge*. Dependiendo de las reglas del equipo, se utilizan diferentes estrategias para mantener el historial de cambios limpio y comprensible.

### 1. Merge Clásico (Recursive / 3-Way Merge)

Es el comportamiento por defecto de Git cuando las ramas han divergido. Crea un nuevo commit especial llamado "merge commit" que une los dos historiales.

* **Ventaja:** Mantiene el contexto histórico exacto de cuándo y cómo se desarrolló una característica.
* **Desventaja:** Si hay muchos desarrolladores, el historial de Git puede convertirse en una "vía de tren" difícil de leer (conocido como *spaghetti history*).
* **Comando:** `git merge <nombre_rama>`

### 2. Fast-Forward Merge

Ocurre cuando la rama principal no ha tenido nuevos commits desde que creaste tu rama. Git simplemente mueve el puntero de la rama principal hacia adelante, hasta tu último commit. No crea un "merge commit".

* **Ventaja:** Mantiene el historial en una línea perfectamente recta.
* **Desventaja:** Se pierde la noción de que esos commits pertenecieron a una rama específica en el pasado.
* **Comando (forzarlo si es posible):** `git merge --ff-only <nombre_rama>`

### 3. Squash y Merge (La opción preferida para repositorios limpios)

Esta estrategia toma todos los commits de tu rama (por ejemplo, 15 pequeños commits de "arreglo un error", "formateo código", etc.) y los "comprime" en un solo commit gigante y bien documentado antes de unirlo a la rama principal.

* **Ventaja:** Mantiene la rama principal impecable. Cada commit en `main` representa una característica terminada, no el proceso de prueba y error del desarrollador.
* **Desventaja:** Pierdes el detalle granular de cómo el desarrollador construyó la característica paso a paso.
* **Comando:** 
  ```bash
  git merge --squash <nombre_rama>
  git commit -m "Característica completada"
  ```

### 4. Git Rebase (La reescritura del historial)

*Rebase* es la alternativa avanzada al merge. En lugar de unir dos ramas con un "merge commit", Rebase toma tus commits de la rama secundaria, "desconecta" la rama, y la vuelve a conectar justo en la punta (el último commit) de la rama principal.

* **Ventaja:** Crea un historial lineal y limpio, como si hubieras escrito tu código hoy mismo basándote en la última versión del proyecto.
* **Desventaja:** Cambia los identificadores (*hashes*) de los commits.
* **Comando:** Estando en tu rama secundaria, ejecutas `git rebase main`

> ⚠️ **Regla de Oro Profesional:** NUNCA hagas rebase sobre una rama pública que otras personas estén utilizando (como `main` o `develop`), ya que destruirás el historial en el que ellos se basan. Úsalo solo en tus ramas locales privadas antes de enviarlas al servidor.

---

## 7. Modelos de Trabajo y Git Workflows

Un equipo necesita acordar cómo va a usar las ramas para no pisarse el trabajo. A esto se le llama "Git Workflow". Aquí tienes los tres más utilizados en la industria:

### 1. Feature Branch Workflow (Basado en Funcionalidades)

Es el modelo más común en equipos modernos y ágiles (muy usado en GitHub a través de *Pull Requests*).

* **Cómo funciona:** Existe una única rama central (`main`). Cada vez que un desarrollador quiere añadir algo, crea una rama nueva a partir de `main` con el nombre de la tarea (ej. `feature/login`). Cuando termina, sube la rama y abre un *Pull Request* (PR) para que otros revisen su código. Si se aprueba, se hace merge a `main`.
* **Ideal para:** Equipos que hacen Integración Continua (CI/CD) y despliegan constantemente.

### 2. Trunk-Based Development (TBD)

Es la práctica dorada en la cultura DevOps (directamente relacionada con el "First Way: Flow").

* **Cómo funciona:** Los desarrolladores trabajan en ramas muy cortas (de horas, no de días) y hacen merge directamente a la rama principal (el "trunk" o `main`) varias veces al día.
* **La clave:** Para que el código incompleto no rompa la aplicación en producción, se usan **Feature Flags** (interruptores en el código que ocultan la funcionalidad a los usuarios hasta que esté 100% lista).
* **Ideal para:** Equipos de muy alto rendimiento que buscan evitar los conflictos de integración masivos que ocurren cuando dos desarrolladores trabajan aislados durante semanas.

### 3. Gitflow Workflow (El modelo estricto)

Es un marco de trabajo muy estructurado diseñado por Vincent Driessen. Fue el estándar de la industria durante años, aunque hoy en día a veces se considera demasiado complejo para equipos de *Continuous Deployment*.

#### Ramas permanentes (Larga duración)
* `main`: Solo contiene código que está en producción y listo para los usuarios.
* `develop`: Es la rama principal de integración. De aquí nacen todas las nuevas ideas.

#### Ramas efímeras (Temporales)
* `feature/*`: Nacen de `develop`. Se usan para desarrollar nuevas características y vuelven a `develop` al terminar.
* `release/*`: Nacen de `develop` cuando se acerca una fecha de lanzamiento. Se usan solo para arreglar últimos bugs antes de producción. Al terminar, se fusionan tanto en `main` como en `develop`.
* `hotfix/*`: Nacen de `main`. Se usan exclusivamente para arreglar errores críticos en producción. Al terminar, se fusionan en `main` y en `develop`.

* **Ideal para:** Proyectos tradicionales con fechas de lanzamiento programadas (ej. software de escritorio, aplicaciones móviles) o industrias muy reguladas donde no se despliega automáticamente todos los días.

## 8. Gestión de Conflictos en Git

Los conflictos son una parte completamente normal del desarrollo en equipo. Ocurren cuando Git no puede fusionar dos ramas automáticamente.

### ¿Qué son y cómo solucionar conflictos?

Un conflicto suele darse porque dos desarrolladores editaron exactamente la misma línea de un archivo, o porque uno borró un archivo que el otro estaba modificando. Cuando esto pasa durante un *merge* o *rebase*, Git detiene el proceso y marca los archivos problemáticos.

Al abrir el archivo en tu editor de código, verás unas marcas generadas por Git:

* `<<<<<<< HEAD`: Indica dónde empiezan tus cambios actuales.
* `=======`: Es el separador entre tu versión y la versión entrante.
* `>>>>>>> nombre-de-la-rama`: Indica los cambios que vienen de la otra rama.

La solución es estrictamente manual: debes borrar esas marcas (`<<<<<<<`, `=======`, `>>>>>>>`) y editar el código dejándolo exactamente como quieres que funcione finalmente. Una vez limpio, guardas el archivo, lo añades con `git add <archivo>` y finalizas con `git commit`.

### Buenas prácticas para evitar conflictos

* **Sincronización constante:** Haz `git pull` o `git fetch/rebase` frecuentemente para mantener tu rama actualizada con los cambios de tus compañeros.
* **Commits pequeños:** Trabaja en ramas de vida corta. Cuanto más tiempo pases sin integrar tu código, mayor será la probabilidad de conflictos masivos.
* **Comunicación:** Avisa al equipo si vas a refactorizar un archivo central o muy utilizado por todos.
* **Modularidad:** Un código bien dividido en archivos pequeños reduce las colisiones.

---

## 9. Deshaciendo Cambios: Checkout, Revert y Reset

Git te ofrece diferentes "máquinas del tiempo". Elegir la correcta depende de si los cambios están solo en tu ordenador o si ya los has compartido.

### Checkout (Descartar cambios locales)

Históricamente, `git checkout` se ha usado tanto para cambiar de rama como para descartar cambios en el directorio de trabajo.

* **Comando:** `git checkout -- <archivo>`
* **Uso:** Si has modificado un archivo pero aún no le has hecho *add* ni *commit*, este comando borra tus ediciones y devuelve el archivo a su estado original (el último commit).
* **Nota actual:** En versiones modernas de Git, se recomienda usar el comando `git restore <archivo>` para esta acción específica, siendo más semántico.

### Revert (La forma segura y pública)

* **Comando:** `git revert <hash-del-commit>`
* **Uso:** Es la forma correcta de deshacer un error que ya has subido (*pusheado*) al repositorio remoto. 
* **Mecanismo:** En lugar de borrar la historia, Git crea un nuevo commit que aplica exactamente los cambios opuestos (si el commit original sumaba una línea, el revert la resta).
* **Ventaja:** Como no reescribes el historial previo, no rompes el trabajo de los compañeros que ya se habían descargado ese código.

### Reset (La máquina del tiempo destructiva)

* **Comando:** `git reset <hash-del-commit>`
* **Uso:** Mueve el historial de tu rama local hacia atrás en el tiempo hasta un commit específico. Elimina los commits posteriores.

Existen tres niveles:
* `--soft`: Mueve el historial, pero deja los cambios de los commits "borrados" en tu Staging Area, listos para un nuevo commit.
* `--mixed` *(por defecto)*: Mueve el historial y deja los cambios en tu directorio de trabajo, pero fuera del Staging Area.
* `--hard`: **Peligroso.** Mueve el historial y destruye absolutamente todos los cambios y archivos modificados desde ese punto. No hay vuelta atrás fácil.

---

## 10. Manipulando la Historia: Rebase, Amend y Cherry-pick

Estas herramientas sirven para reorganizar, limpiar y "maquillar" tus commits antes de compartirlos. 

> ⚠️ **Regla de oro:** Úsalas solo en tus ramas locales; nunca reescribas la historia de una rama pública como `main`.

### Rebase

Como vimos en estrategias de merge, `git rebase` cambia el punto de partida (la base) de tu rama.

* **Modo Interactivo (`git rebase -i <commit-base>`):** Es una herramienta increíblemente potente. Te permite abrir un editor donde puedes unir múltiples commits en uno solo (*squash*), reordenarlos, editar sus mensajes o incluso eliminar commits específicos antes de hacer un *Pull Request*.

### Amend (Corrigiendo el último commit)

* **Comando:** `git commit --amend`
* **Uso:** Modifica el último commit que acabas de hacer.

Casos típicos:
* **Error tipográfico:** Si te equivocaste en el mensaje del commit, ejecutas el comando y se abrirá el editor para que lo cambies.
* **Olvidaste un archivo:** Si hiciste commit pero olvidaste guardar un archivo, simplemente haz `git add <archivo-olvidado>` y luego `git commit --amend --no-edit`. Se añadirá al commit anterior silenciosamente.

### Cherry-pick (Seleccionando commits a la carta)

* **Comando:** `git cherry-pick <hash-del-commit>`
* **Uso:** Permite "robar" o copiar un commit específico de cualquier otra rama y aplicarlo directamente en tu rama actual.
* **Caso típico:** Un compañero ha subido a su rama un parche urgente para un bug que te está bloqueando. En lugar de fusionar toda su rama (que puede estar inestable), haces *cherry-pick* solo de ese commit que contiene la solución.
