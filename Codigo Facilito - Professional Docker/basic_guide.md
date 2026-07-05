# 🐳 The Ultimate Docker Commands Guide: From Scaffolding to Deployment

> **Role:** Quick Reference Manual for Junior Developers  
> **Author:** Senior Dev / DevOps Lead  
> **Status:** ✅ Production Ready  

---

**This guide covers the essential Docker commands organized by their lifecycle and purpose.** In your day-to-day work, you will look back at this cheat sheet constantly until it becomes pure muscle memory.

---

## 🔧 1. System Information & Diagnostics

**Before touching any containers, you need to know the current state of your Docker engine.**

| Command | Purpose |
|---------|---------|
| `docker version` | Shows Docker version on client and engine (server). Checks API compatibility. |
| `docker info` | System report: container count, images, storage driver, resources (CPU/Memory). |
| `docker system df` | Disk usage breakdown: images, containers, volumes, build caches. |
| `docker --help` | Lists all commands with descriptions. |

---

## 📦 2. Image Management (`docker image`)

**Images are the "read-only" templates used to spin up containers.**

| Command | Purpose |
|---------|---------|
| `docker images` or `docker image ls` | Lists all images currently downloaded locally. |
| `docker pull <image>:<tag>` | Downloads image from registry (Docker Hub default). Omit `:tag` to pull `:latest`. Example: `docker pull postgres:15-alpine` |
| `docker rmi <image_id_or_name>` | Removes image from local storage (cannot delete if in-use). |
| `docker image prune` | Removes all dangling images. Use `-a` to delete all unused images. |
| `docker build -t <name>:<tag> .` | Builds custom image from `Dockerfile` in current directory. |

---

## 🚀 3. Container Lifecycle (`docker container`)

**A container is the live, executable instance of an image.**

| Command | Purpose | Key Flags |
|---------|---------|-----------|
| `docker ps` | Lists **currently active (running)** containers. | — |
| `docker ps -a` | Lists **all** containers (running, stopped, failed). | — |
| `docker run [flags] <image>` | **Creates AND starts** container from image. Auto-pulls if not found. | `-d`, `-p 8080:80`, `--name`, `-it` |
| `docker stop <id/name>` | Gracefully stops container (sends `SIGTERM`). | — |
| `docker kill <id/name>` | Force-stops container immediately (sends `SIGKILL`). Use only if frozen. | — |
| `docker start <id/name>` | Restarts stopped container (preserves changes). | — |
| `docker restart <id/name>` | Stops and restarts container. | — |
| `docker rm <id/name>` | Permanently deletes stopped container. | `-f` (force delete running) |
| `docker container prune` | Removes all stopped containers. | — |

#### Common `docker run` Flags:
- **`-d`** → Detached mode (background)
- **`-p 8080:80`** → Port mapping (host:container)
- **`--name my_container`** → Assign friendly name
- **`-it`** → Interactive shell access

---

## 🔍 4. Inspection, Logs & Debugging

**When things go wrong, this is where we seniors spend most of our time.**

| Command | Purpose | Examples/Flags |
|---------|---------|---|
| `docker logs <id/name>` | Display container's STDOUT/STDERR output. | `-f` (follow), `--tail 50` (last 50 lines) |
| `docker exec -it <id/name> <cmd>` | Run command inside **running** container. Jump in to investigate. | `docker exec -it my_db psql -U postgres` |
| `docker inspect <id/name/image>` | Returns JSON with all details: IPs, mounts, env vars, start commands. | — |
| `docker top <id/name>` | Lists active OS processes running inside container in real-time. | — |
| `docker stats` | Real-time stream: CPU, Memory, network, disk for all active containers. | — |

---

## 💾 5. Data Persistence: Volumes (`docker volume`)

**By default, containers are ephemeral; if you delete them, their data dies with them. Volumes break that rule.**

| Command | Purpose |
|---------|---------|
| `docker volume ls` | Lists all volumes created on Docker engine. |
| `docker volume create <name>` | Creates isolated storage space managed by Docker. |
| `docker volume inspect <name>` | Shows physical path where Docker saves files on host OS. |
| `docker volume rm <name>` | Deletes volume (cannot delete if attached to container). |
| `docker volume prune` | Removes all unused volumes. **⚠️ Risk of data loss!** |

> 📌 **Attach Volume to Container:** Use the `-v` flag when creating container:
> ```bash
> docker run -d -v my_volume:/container_data redis
> ```

---

## 🌐 6. Communication: Networks (`docker network`)

**Allows different containers to discover and securely talk to each other.**

| Command | Purpose |
|---------|---------|
| `docker network ls` | Lists all networks (default: `bridge`, `host`, `none`). |
| `docker network create --driver <type> <name>` | Creates new network. Example: `docker network create my_local_network` |
| `docker network connect <network> <container>` | Connects running container to specific network. |
| `docker network disconnect <network> <container>` | Disconnects container from network. |
| `docker network inspect <network>` | Shows network info: connected containers and internal IPs. |
| `docker network prune` | Cleans up all unused networks. |

---

## 🧹 7. Maintenance & Absolute Cleanup

**The emergency commands when you run out of hard drive space due to accumulated test containers.**

| Command | Purpose | ⚠️ Warning |
|---------|---------|---|
| `docker system prune` | Deletes stopped containers, unused networks, and dangling image data. | Safe, only removes unused items. |
| `docker system prune -a --volumes` | **THE NUCLEAR OPTION:** Wipes ALL inactive items: containers, images, volumes. | 🔥 **Irreversible!** |

## 🔐 8. Environment Variables (Configuration & Secrets)

**⚠️ NEVER hardcode passwords, API keys, or config directly into code or `Dockerfile`** — massive security risk!

Docker embraces the **12-Factor App methodology**, passing configuration dynamically at runtime via Environment Variables.

### 📝 Method 1: Passing Variables via CLI

Inject environment variables using the `-e` flag (chain multiple flags):

```bash
# Single variable
docker run -e "KEY=value" <image>

# Multiple variables example
docker run -d -e "MYSQL_ROOT_PASSWORD=supersecret" -e "MYSQL_DATABASE=myapp" mysql:8
```

### 📋 Method 2: Using an Environment File (`.env`)

For dozens of variables, create a `.env` file (one `KEY=VALUE` per line) and load it:

```bash
# Load all variables from .env file
docker run --env-file <path_to_file> <image>

# Example
docker run -d --env-file ./production.env my_node_app
```

### 🔎 Method 3: Inspecting & Debugging Variables

Verify if the container received correct credentials or URLs:

```bash
# See all env vars inside running container
docker exec -it <container_name> env

# See env vars in JSON output (look for "Env": [...] array)
docker inspect <container_name>
```

> 🛡️ **Senior Security Tip:**
> - **Never** commit `.env` files to Git repository (add to `.gitignore`)
> - In **production**, env vars in plain text are risky (visible via `docker inspect`)
> - Enterprise solution: Use **Docker Secrets** or external vaults (HashiCorp Vault, AWS Secrets Manager)

## 🚪 9. Deep Dive: How Port Mapping Works (`-p` under the hood)
By default, containers are completely isolated from the outside world. If you run a web server inside a container on port `80`, your computer (the host) doesn't know it exists, and you cannot access it through your browser. 

To bridge this gap, Docker uses **Port Mapping** (or Port Forwarding). 

When you use the flag `-p <Host_Port>:<Container_Port>`, you are telling the Docker engine: *"Listen for traffic on my physical computer's port X, and intercept it to send it directly to the container's internal port Y."*

```text
  [ Outside World / Browser ] 
              │
              ▼ (Traffic hits your physical machine)
   ┌─────────────────────────────────────────┐
   │ Host Machine (Your PC)                  │
   │  └─► Port 8080 (Mapped via -p 8080:80)  │
   │        │                                │
   │        ▼ (Docker Network Bridge)        │
   │   ┌───────────────────────────────┐     │
   │   │ Isolated Container            │     │
   │   │  └─► Port 80 (Web Server)     │     │
   │   └───────────────────────────────┘     │
   └─────────────────────────────────────────┘
```

### ⚡ Critical Rules to Remember

| Rule | Details |
|------|---------|
| **Left = Host, Right = Container** | In `-p 8080:80`: `8080` is YOUR port, `80` is container's port. |
| **No Host Port Collisions** | Can't map 2 containers to same host port (`-p 80:80` + `-p 80:3000` = crash). BUT: Multiple host→same container OK (`-p 8081:80` + `-p 8082:80`). |
| **Random Port Allocation** | Omit host port: `docker run -p 80 nginx` → Docker picks random unused port (e.g., 32768). |

---

## 📊 10. Managing and Inspecting Ports via CLI

**Managing multiple microservices? Know exactly which ports are exposed:**

| Command | Purpose | Example Output |
|---------|---------|---|
| `docker ps` | Check ports in `PORTS` column (simplest way). | `0.0.0.0:8080->80/tcp` = host:8080 → container:80 |
| `docker port <container>` | Lists all port mappings for a container. | `80/tcp -> 0.0.0.0:8080` |
| `docker port <container> <port>` | Query mapping for ONE specific container port. | `docker port my_db 5432` |
| `docker run -P <image>` (Capital P) | Publish ALL exposed ports (auto-map to random ports). | Great for quick testing, no collision risk. |

> 🔒 **Senior Security Tip**: By default, `-p 8080:80` binds to `0.0.0.0`, meaning your container's port is open to your local machine and anyone on your same Wi-Fi/local network. If you only want to access the container from your own computer securely, force it to bind to localhost like this:
> ```bash
> docker run -d -p 127.0.0.1:8080:80 nginx
> ```

## 🔗 11. Docker Volumes and Data Sharing

**Containers are stateless & ephemeral by design.** Delete them → lose all data. **Volumes** solve this.

### 🔧 How Data Storage Works Under the Hood

When you create a Docker Volume, Docker allocates a directory on your host HD (typically `/var/lib/docker/volumes/` in Linux) managed exclusively by Docker Engine. 

```text
 ┌────────────────────────────────────────────────────────┐
 │ Host Machine (Your PC)                                 │
 │  ┌──────────────────────────────────────────────────┐  │
 │  │ Protected Docker Storage                         │  │
 │  │  └─► /var/lib/docker/volumes/my_shared_data/     │  │
 │  └──────────────────────────┬───────────────────────┘  │
 │                             │                          │
 │            ┌────────────────┴────────────────┐         │
 │            ▼ (Mounted Simultaneously)        ▼         │
 │   ┌─────────────────┐               ┌─────────────────┐│
 │   │ Container A     │               │ Container B     ││
 │   │  └─► /app/logs  │               │  └─► /backup    ││
 │   └─────────────────┘               └─────────────────┘│
 └────────────────────────────────────────────────────────┘
```

When you mount a volume, Docker bypasses the container's Union File System (UnionFS) and points the container's internal folder directly to the host directory. This results in zero performance overhead and decoupled lifecycles.

### 🤝 Sharing Volumes Between Multiple Containers

**Multiple containers can mount the SAME volume simultaneously** = Standard pattern for decoupling app logic.

#### 📍 Step-by-Step Example:

**Step 1:** Create a Named Volume
```bash
docker volume create shared_storage
```

**Step 2:** Container A (Writer)
```bash
docker run -d --name web_app -v shared_storage:/var/log/app nginx
```

**Step 3:** Container B (Reader)
```bash
docker run -d --name log_analyzer -v shared_storage:/data alpine tail -f /data/access.log
```

✅ **Result:** Data written by `web_app` → `/var/log/app` instantly appears in `log_analyzer` → `/data`

### 📌 Useful Commands for Volume Sharing

| Command | Purpose |
|---------|---------|
| `docker volume ls` | Lists all volumes on Docker engine. |
| `docker volume rm <name>` | Deletes volume (cannot delete if attached to container). |
| `docker run --volumes-from <source> <image>` | **Inherit volumes** from another container (copy all mount definitions). |
| `docker run -v <volume>:<path>:ro <image>` | **Read-Only Mount** (`:ro`). Protects files from modification by other containers. |

## 🔗 12. Docker Networking & Communication

**By default, Docker containers run in complete isolation.** Real-world apps need components to communicate (frontend → backend → database) and with the outside world. **Docker Networks** handle this secure communication.

### 📡 The Network Drivers

When you create a network, choose a "driver" that dictates its behavior:

| Driver | Behavior | Use Case |
|--------|----------|----------|
| **`bridge`** (Default) | Private virtual network. Containers can talk securely but are hidden from outside unless you use `-p`. | Multi-container apps on single host |
| **`host`** (Performance) | Removes isolation completely. Container shares host's network stack directly. | High-performance apps, max throughput |
| **`none`** | Disables networking entirely. Container is completely isolated. | Testing, sandboxed environments |
| **`overlay`** | Connects containers across multiple physical servers. | Docker Swarm deployments |

### Deep Dive: The Host Network (`--network host`)
While the `bridge` driver is all about isolation, the **`host` network driver** is about removing that isolation entirely. 

When you run a container with `--network host`, Docker does not allocate a private IP address for the container, nor does it create a virtual network bridge. Instead, the container shares your physical machine's networking stack directly.

    [ Outside World / Internet ]
               |
               V
    [ Your Physical Machine (Host IP) ]
    (Port 80) <--- Binds Directly
               |
    [ Container running on --network host ]

### 🚀 Deep Dive: The Host Network (`--network host`)

**The `bridge` driver = isolation. The `host` driver = zero isolation.**

```
[ Outside World / Internet ]
         ↓
[ Your Physical Machine (Host IP) ]
   Port 80 ← Binds Directly
         ↓
[ Container on --network host ]
```

#### ⚡ Key Behaviors:

| Behavior | Impact |
|----------|--------|
| **No Port Mapping Needed** | No `-p` flags. Port 80 in container = Port 80 on host automatically. |
| **Maximum Performance** | No virtual bridge or NAT → bare-metal network speed. |
| **Port Collisions** | Can't run 2 containers on same port. If port 80 in use, fails. |
| **Mac/Windows Gotcha** | Works on Linux. On Docker Desktop, "host" = hidden Linux VM. |

### ✨ The Magic of Custom Bridge Networks (Internal DNS)

**HUGE difference:** Default bridge ≠ Custom bridge.

On **custom networks**, Docker provides **automatic DNS resolution** — containers ping by name instead of IP!

```
┌─────────────────────────────────────────┐
│ Docker Custom Network: backend_net      │
├─────────────────────────────────────────┤
│ Container: api_app    Container: db_main│
│ IP: 172.18.0.2   ←→   IP: 172.18.0.3   │
│ (Pings using name "db_main")            │
└─────────────────────────────────────────┘
```

### 📊 Essential Networking Commands

| Command | Purpose | Example |
|---------|---------|---------|
| `docker network ls` | Lists all networks on Docker engine. | — |
| `docker network create <name>` | Creates custom bridge network. | `docker network create my_microservices_net` |
| `docker run -d --name <name> --network <net> <image>` | Start container on custom network. | `docker run -d --name db --network my_net postgres` |
| `docker run -d --name <name> --network host <image>` | Container uses host's network stack directly. | — |
| `docker network connect <net> <container>` | Connect running container to network on-the-fly. | — |
| `docker network disconnect <net> <container>` | Disconnect running container from network (no stop). | — |
| `docker network inspect <network>` | Show detailed JSON: containers, internal IPs. | — |
| `docker network prune` | Delete all unused networks. | — |

> 🏗️ **Senior Architecture Tip:**
> - **NEVER** use default `bridge` for multi-container apps (no DNS resolution)
> - **ALWAYS** create custom network
> - Use container names in connection strings: `postgres://user:pass@db_main:5432/mydb`
> - NOT `localhost:5432` (doesn't resolve in containers)

## 🏗️ 13. The Core of Docker: Images & Dockerfiles

**Before spinning up containers, you need images.** Understanding how images work = Junior vs Senior DevOps engineer difference.

### 🔍 What is a Docker Image and How Does it Work?

**Docker Image** = lightweight, standalone executable package with everything: source code, runtime, libraries, config.

| Concept | Details |
|---------|---------|
| **Read-Only & Immutable** | Once built, cannot be modified. Change code → rebuild entire image. |
| **Layered Architecture (UnionFS)** | Built from stacked independent layers. Each Dockerfile command = new layer. Docker caches layers → only rebuild changed layer = fast builds. |

### 📋 The Dockerfile: Essential Instructions Breakdown

**Dockerfile** = plain-text config file read top-to-bottom. Sequential blueprint to build image.

| Instruction | Purpose | Examples |
|-------------|---------|----------|
| **`FROM`** | Base OS/runtime. MUST be first. | `FROM node:18-alpine` (tiny Node 18) \| `FROM ubuntu:22.04` |
| **`WORKDIR`** | Internal app directory. All commands run here. | `WORKDIR /usr/src/app` (creates if missing) |
| **`COPY`** | Copy files from host to image. Do dependencies first. | `COPY package.json ./` \| `COPY . .` (all files) |
| **`RUN`** | Execute Linux commands during BUILD. Install packages. | `RUN npm install` \| `RUN apt-get update && apt-get install -y curl` |
| **`ENV`** | Hardcode default environment variables. | `ENV NODE_ENV=production` \| `ENV PORT=8080` |
| **`EXPOSE`** | Document which ports app listens on (informational). | `EXPOSE 8080` (doesn't actually publish) |

### ⚙️ Execution Commands: `ENTRYPOINT` vs `CMD`

**When image is built**, container needs to know what program to execute. Define with `ENTRYPOINT` or `CMD`.

| Command | Purpose | Example | Behavior |
|---------|---------|---------|----------|
| **`CMD`** | Default arguments to start service. | `CMD ["node", "server.js"]` | **Easily overwritten** — `docker run my_app bash` replaces it with `bash`. |
| **`ENTRYPOINT`** | Fixed, strict executable. | `ENTRYPOINT ["python", "script.py"]` | **Cannot be replaced** — extra args appended. `docker run my_image --help` = `python script.py --help`. |

---

## 🎯 14. Executing Services (The `docker run` Mastery)

Once Dockerfile → image (`docker build -t my_app:v1 .`), execute as running service. Configure runtime: ports, storage, secrets.

### 📊 The Service Lifecycle

**Container stays alive only as long as main process (CMD/ENTRYPOINT) runs.** Server crashes → container stops.

### 📍 Executing a Service: Step-by-Step

Production-ready = multiple flags combining perfectly:

| Step | Command | Purpose |
|------|---------|---------|
| 1️⃣ | `docker run -d my_app:v1` | **Run in background** (detached mode, frees terminal) |
| 2️⃣ | `docker run -d --name backend_api my_app:v1` | **Name the service** (no memorizing random IDs) |
| 3️⃣ | `docker run -d --name backend_api -p 8080:3000 my_app:v1` | **Map ports** (host:container) |
| 4️⃣ | `docker run -d --name backend_api -p 8080:3000 -e "DB_PASS=secret" my_app:v1` | **Inject env vars** (override defaults, pass secrets) |
| 5️⃣ | `docker run -d --name backend_api -p 8080:3000 -v app_data:/app/data my_app:v1` | **Attach volumes** (persistent data across restarts) |

### 🚀 The Ultimate Production Command

When a Senior DevOps engineer deploys a service via CLI:

```bash
docker run -d \
  --name production_api \
  --network microservices_net \
  -p 443:8443 \
  -e "NODE_ENV=production" \
  --env-file ./secrets.env \
  -v api_logs:/var/log/app \
  --restart unless-stopped \
  my_company/backend_api:v2.1
```

> 🔑 **Key Flag:** `--restart unless-stopped` = Auto-reboot if crash OR server restarts.

---

## 📤 15. Docker Hub: Sharing & Deploying Your Images

**Image built locally?** Push to registry (Docker Hub) so developers/production servers can download it worldwide.

### 🔄 Step-by-Step Pipeline:

| Step | Command | Purpose |
|------|---------|---------|
| 1️⃣ | Visit hub.docker.com | Create new public/private repository |
| 2️⃣ | `docker login` | Authenticate securely to Docker Hub |
| 3️⃣ | `docker tag my_app:v1 user/my_app:latest` | Rename (tag) image with username |
| 4️⃣ | `docker push user/my_app:latest` | Upload tagged image to cloud registry |
| 5️⃣ | `docker run -d -p 80:8080 user/my_app:latest` | Any server downloads & runs instantly (no source code needed) |

## 🎭 16. Docker Compose: Orchestrating Multi-Container Apps

**Managing multiple containers via CLI = nightmare** (frontend + backend + database = 5+ commands). **Docker Compose** solves this.

### ❓ What is Docker Compose?

**Tool for defining & running multi-container apps** using a single YAML file (`docker-compose.yml`) instead of dozens of `docker run` flags.

| Benefit | Impact |
|---------|--------|
| **Automation** | Start entire stack: `docker-compose up` (one command!) |
| **Config as Code** | Environment (networks, volumes, vars) version-controlled in Git |
| **Reproducibility** | Any dev clones repo → runs exact same stack, any OS |

### 📝 The Structure of `docker-compose.yml`

Compose treats services as "services" (not containers). Auto-creates networks & volumes.

```yaml
version: '3.8'

services:
  # Service 1: Backend API
  api:
    build: ./backend          # Build from local Dockerfile
    ports:
      - "8080:3000"          # Host:Container port mapping
    environment:              # Pass environment variables
      - DB_HOST=db
    networks:
      - app-network           # Connect to custom network

  # Service 2: PostgreSQL Database
  db:
    image: postgres:15        # Use pre-built image
    volumes:
      - db-data:/var/lib/postgresql/data  # Persistent storage
    networks:
      - app-network

networks:
  app-network:
    driver: bridge

volumes:
  db-data:
```

### 📊 Essential Compose Commands

Forget complex `docker run`. Compose management is simple:

| Command | Purpose |
|---------|---------|
| `docker-compose up -d` | Start entire stack in background (detached mode) |
| `docker-compose ps` | List status of all Compose containers |
| `docker-compose stop` | Stop all services (preserve containers & data) |
| `docker-compose down` | Cleanup: stop & remove containers, networks. Add `--volumes` to wipe data. |
| `docker-compose logs -f <service>` | Follow logs for specific service (e.g., `logs -f api`) |
| `docker-compose exec <service> bash` | Jump into shell of specific service |

---

### ✨ Why use Compose for Multi-Container Apps?

| Feature | Benefit |
|---------|---------|
| **Automatic Networking** | Compose creates shared network. Services reach each other by name (`db`) without IP addresses. |
| **Service Dependencies** | Use `depends_on` to ensure database starts before API connects. |
| **Unified Management** | Update code → `docker-compose up --build` rebuilds only changed images, restarts only affected services. |

> 💡 **Senior Pro-Tip:**
> - Use `docker-compose.yml` for **local dev** (team parity)
> - Keep **production deployment separate**
> - Graduation path: Compose → Kubernetes/Docker Swarm for large scale

---

## 🤖 17. Introduction to Orchestration: The Kubernetes Era

**10 containers = manageable. 1,000 containers = IMPOSSIBLE without Orchestrator.**

### 🎯 What is an Orchestrator?

**Automation engine** managing container lifecycle across cluster of nodes. Handles:

| Capability | Impact |
|------------|--------|
| **Scaling** | Auto-add containers when traffic spikes |
| **Self-Healing** | Container crashes → auto-restart instantly |
| **Load Balancing** | Distribute traffic efficiently across containers |

### 🌟 Kubernetes (K8s): The Industry Standard

**Most popular open-source orchestrator.** "Brain" monitoring cluster state.

| Component | Role |
|-----------|------|
| **Nodes** | Worker machines (physical/VMs) where containers run |
| **Pods** | Smallest unit in K8s. Wrapper for 1+ containers. Never run container directly. |
| **Control Plane** | "Master" node making cluster decisions |

### 💻 Get Started Locally (No Data Center Needed)

| Tool | Purpose |
|------|---------|
| **Minikube** | Single-node K8s cluster in VM on laptop |
| **Kind** | K8s nodes as Docker containers (fast, CI/CD perfect) |
| **K3s** | Lightweight, production-ready (edge devices, labs) |

### 📊 Essential Kubernetes Commands (`kubectl`)

| Command | Purpose |
|---------|---------|
| `kubectl get nodes` | Show all worker machines in cluster |
| `kubectl get pods` | List running pods |
| `kubectl create deployment <name> --image=<img>` | Start deployment (group of identical pods) |
| `kubectl expose deployment <name> --type=NodePort --port=80` | Expose pods to outside network |
| `kubectl logs <pod>` | View pod output/logs |
| `kubectl delete pod <pod>` | Delete pod (auto-restarts if in Deployment) |
| `kubectl apply -f config.yaml` | **Most important**: Apply config from YAML file |

### 🔄 How it Works: The Declarative Model

**Docker = imperative** (\"Run this container\"). **Kubernetes = declarative** (\"Desired state\").

```
1. Write YAML Manifest: \"I want 3 replicas of my API\"
   ↓
2. Submit to cluster: kubectl apply -f app.yaml
   ↓
3. K8s Controller Loop:
   - Current: 2 pods
   - Desired: 3 pods
   - Action: Auto-spin 1 new pod → reach 3
```

> ⚠️ **Senior Architecture Tip:**
> - K8s is powerful but NOT a silver bullet
> - Small app? Stick with Docker Compose
> - Only migrate if you REALLY need orchestration
> - K8s learning curve is significant

---

## 🔌 18. Docker Engine API

**Beyond CLI = Docker provides REST API** controlling daemon programmatically. Powers: VS Code, Portainer, CI/CD pipelines.

### 📡 How It Works

**Docker daemon listens on socket** (Linux: `/var/run/docker.sock`). Interact via HTTP requests: `GET`, `POST`, `DELETE`.

| Method | Endpoint | Purpose | Example |
|--------|----------|---------|---------|
| **POST** | `/containers/create` | Spin up service programmatically | `POST /containers/create` with JSON config → returns container ID |
| **GET** | `/containers/<id>/logs` | Stream container logs | `GET /containers/abc123/logs?stdout=1` |
| **POST** | `/containers/<id>/stop` | Stop running container | `POST /containers/abc123/stop` |

### 🚀 Use Case: Custom Automation

**Instead of shell scripts** with dozens of `docker run` flags:

```bash
# Imperative (error-prone, hard to maintain):
docker run -d --name api -p 8080:3000 -e "DB=prod" my_app:v1

# Declarative (API-driven automation):
POST /containers/create HTTP/1.1
{
  "Image": "my_app:v1",
  "HostConfig": {"PortBindings": {"3000/tcp": [{"HostPort": "8080"}]}},
  "Env": ["DB=prod"]
}
```

> ⚠️ **Security Note:**
> Docker socket = root-level host access. Never expose `/var/run/docker.sock` to internet without TLS authentication.

---

## 🎨 19. Portainer: The GUI Dashboard

**While CLI = king for DevOps**, Portainer = industry-standard GUI translating complex commands into visual web interface.

### 🖥️ What It Does

| Feature | Benefit |
|---------|---------|
| **Container Management** | Manage containers, images, volumes, networks with clicks (no SSH needed) |
| **Real-Time Monitoring** | Immediate visual feedback: CPU/RAM usage, container health status |
| **Stack Deployment** | Deploy `docker-compose.yml` stacks directly through web browser |
| **Log Inspection** | View logs without terminal access |

### 🚀 Installation (Docker-in-Docker Pattern)

**Deploy Portainer as container itself** (bootstrap pattern):

```bash
docker run -d -p 9000:9000 --name=portainer \
  -v /var/run/docker.sock:/var/run/docker.sock \
  portainer/portainer-ce
```

Then access: `http://localhost:9000`

> 💡 **Perfect For:**
> - Teams needing visual inspection without SSH access
> - Ops teams managing multiple Docker hosts
> - Quick container health checks during incidents

---

## 🖥️ 20. Running GUI Applications in Docker: X11 & VS Code

**Challenge: Containers have no native display.** Solution = "tunnel" graphical output to host's display server.

### 🖼️ Running X11 GUI Apps (Linux Only)

**Linux uses X11 window system.** To run GUI app (Firefox, IDE) inside container, share host's display socket.

| Step | Command | Purpose |
|------|---------|---------|
| 1️⃣ | `xhost +local:docker` | Grant container permission to access host's display |
| 2️⃣ | `docker run -it -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix my-gui-image` | Mount X11 socket + pass DISPLAY env var |
| 3️⃣ | Application launches | GUI renders on host (not inside container) |

**Example: Running Firefox in Docker:**
```bash
docker run -it \
  -e DISPLAY=$DISPLAY \
  -v /tmp/.X11-unix:/tmp/.X11-unix \
  firefox:latest
```

### 💻 VS Code & Development Containers (Best Practice)

**VS Code solved this elegantly.** Instead of installing dependencies on laptop:

| Traditional | VS Code + Docker |
|-------------|------------------|
| ❌ Install Node 18, Python 3.11, Go locally | ✅ Everything in container |
| ❌ \"It works on my machine\" syndrome | ✅ Team works in identical environment |
| ❌ Hard to switch between projects (version conflicts) | ✅ Per-project, isolated toolchain |

### ⚙️ How Dev Containers Work

1. **Install Extension**: \"Dev Containers\" (Remote - Containers)
2. **Create Config**: `.devcontainer/devcontainer.json` in project root
3. **Connect**: VS Code → remote Docker session
4. **Code**: Source mounted as volume, all extensions/debugger run inside container

**Example `.devcontainer.json`:**
```json
{
  "name": "Node.js App",
  "image": "node:18-alpine",
  "postCreateCommand": "npm install",
  "customizations": {
    "vscode": {
      "extensions": ["ms-python.python", "ms-vscode.cpptools"]
    }
  }
}
```

**Team workflow:**
```
Team Member A: git clone → Opens folder in VS Code
              → Prompt: \"Reopen in Container?\" → YES
              → VS Code builds image + connects
              → Exact same environment as Team Member B
```

> 🏆 **Why This Matters:**
> - Eliminates \"dependency drift\" across team
> - Onboarding new devs: `git clone` + one click
> - CI/CD uses same Dockerfile (parity guaranteed)
> - Works on Windows/Mac/Linux (Docker Desktop handles virtualization)
