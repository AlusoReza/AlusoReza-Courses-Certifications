# Astro Framework: The Ultimate Beginner's Guide

Astro has become one of the most popular frameworks for web development due to its unique approach to performance. Here is a comprehensive guide covering its fundamentals, how to start, and how its file structure works.

---

## 1. What is Astro and What is it Used For?

Astro is a modern web framework designed for building blazing-fast, content-focused websites. Unlike traditional Single Page Application (SPA) frameworks (like Next.js or standard React/Vue), Astro takes a different approach to how it renders web pages.

### Core Concepts

* **Zero JavaScript by Default:** Astro renders your entire site to static HTML on the server. By default, it sends zero JavaScript to the browser, making your website incredibly fast to load.
* **Astro Islands (Islands Architecture):** If you need interactivity (like a dark mode toggle or an image carousel), Astro allows you to create "islands" of interactivity using your favorite UI framework (React, Vue, Svelte, Tailwind, etc.). Only those specific components will load JavaScript, while the rest of the page remains static HTML.
* **Framework Agnostic:** You can use Astro's native `.astro` syntax, or seamlessly plug in components from React, Vue, Solid, or Svelte within the same project.

### What is it mainly used for?

Because of its focus on speed and SEO, Astro is the perfect tool for content-driven websites. Its primary use cases include:

* Blogs and Personal Portfolios
* Documentation websites
* E-commerce storefronts
* Marketing and landing pages
* Corporate websites

---

## 2. How to Initialize an Astro Project

Starting a new Astro project is straightforward. You just need to have Node.js installed on your machine.

Open your terminal and run the following command:

```bash
# Run the Astro setup wizard
npm create astro@latest
```

The wizard will ask you a few simple questions:
1. Where should we create your new project? (e.g., `./first-astro-project`)
2. How would you like to start your new project? (You can choose a blank canvas, a blog template, or include sample files).
3. Do you plan to write TypeScript? (Yes/No).
4. Install dependencies? (Yes).
5. Initialize a new git repository? (Yes).

Once the setup is complete, navigate into your project folder and start the development server:

```bash
cd first-astro-project
npm run dev
```

Your new site will be live at `http://localhost:4321`.

---

## 3. Project Structure Explained

Based on the structure shown in your reference `image_eb2cfe.png`, here is a detailed breakdown of what each folder and file means in a standard Astro project (`first-astro-project`):

### The Directories

* **`.vscode/`:** Contains workspace settings and recommended extensions specific to Visual Studio Code to ensure your editor handles Astro files correctly.
* **`node_modules/`:** This is where Node.js stores all the external packages and dependencies required for your project to run. *(You should never edit files in here manually).*
* **`public/`:** This directory is for static assets that do not need to be processed by Astro's bundler. Files here are placed at the root of your final website. As seen in the image, it contains `favicon.ico` and `favicon.svg`. If you reference them, you just use `/favicon.ico` in your HTML.
* **`src/`:** The most important folder. This is where your actual source code lives.
  * **`src/assets/`:** Unlike the `public/` folder, images and files here (like `astro.svg` and `background.svg`) are processed, optimized, and bundled by Astro.
  * **`src/components/`:** This is where you put reusable pieces of UI. In the image, we see `Welcome.astro`. Components don't create new web pages; they are imported and used inside pages or layouts.
  * **`src/layouts/`:** Contains template wrappers like `Layout.astro`. A layout defines the common structure of your HTML (like the `<head>`, `<nav>`, and `<footer>`). Your individual pages will inject their specific content into this layout.
  * **`src/pages/`:** Astro uses file-based routing. Every `.astro` or `.md` file inside this folder automatically becomes a route (a page) on your website. `index.astro` is your homepage (`/`).

### The Root Files

* **`.gitignore`:** Tells Git which files and folders (like `node_modules/`) it should not upload to your repository.
* **`AGENTS.md` & `CLAUDE.md`:** These are custom Markdown files added to this specific project (not standard to Astro). They are typically used to provide specific instructions or context for AI coding assistants.
* **`astro.config.mjs`:** The brain of your Astro project. Here is where you configure integrations (like adding Tailwind or React), define your site's URL, and set rendering modes.
* **`package.json` & `package-lock.json`:** Standard Node.js files. They list your project's metadata, the exact versions of the dependencies installed, and define scripts (like `npm run dev`).
* **`README.md`:** The instruction manual for your project, usually displayed on GitHub.
* **`tsconfig.json`:** The configuration file for TypeScript, ensuring your editor provides the correct type-checking and autocompletion for Astro.
* **`basic_concepts.md`:** Another custom Markdown file created for this project, likely containing notes or documentation for the developer.
## 4. Styling in Astro (CSS)

Astro provides a fantastic and highly flexible styling experience out of the box. You do not need to install any extra tools to write standard CSS, Sass, or CSS Modules.

### 1. Scoped CSS (The Astro Default)

By default, any CSS you write inside an `.astro` file is scoped to that specific component. This is a huge advantage because it means your styles will never accidentally leak and break other parts of your website.

To use scoped CSS, simply add a `<style>` tag at the bottom of your component:

```html
<style>
  /* Estos estilos solo afectarán a este componente */
  h1 {
    color: purple;
  }
</style>
```

### 2. Global CSS

Sometimes you need styles to apply to the entire website (like resetting margins, setting base fonts, or defining CSS variables). You have two ways to do this:

* **Option A: The `is:global` directive**  
  You can tell Astro to make a `<style>` tag global by adding `is:global`.
  ```html
  <style is:global>
    /* Esto afectará a toda la aplicación */
    body {
      margin: 0;
      font-family: sans-serif;
    }
  </style>
  ```
* **Option B: Importing a global CSS file**  
  The most common professional practice is to create a `src/styles/global.css` file and then import it into your main layout (`src/layouts/Layout.astro`).

Inside the frontmatter (the area between the `---` dashes at the top of your Astro file):

```javascript
---
import '../styles/global.css';
---
```

---

## 5. Supercharging Astro: Installing Tailwind CSS and React

One of Astro's greatest superpowers is its `astro add` command. This command automates the installation and configuration of integrations, so you don't have to manually edit complex configuration files.

Here is the step-by-step guide to adding Tailwind CSS (for styling) and React (for UI components).

### Step 1: Install Tailwind CSS

Tailwind is a utility-first CSS framework that lets you style elements directly in your HTML using predefined classes (e.g., `text-center`, `bg-blue-500`).

1. Open your terminal in the root of your Astro project.
2. Run the following automatic integration command:
   ```bash
   npx astro add tailwind
   ```

The terminal will ask you a series of questions:
* Install the following dependencies? *(Press Enter for Yes)*.
* Generate a `tailwind.config.mjs` file? *(Press Enter for Yes)*.
* Update your `astro.config.mjs` file? *(Press Enter for Yes)*.

**What just happened?**  
Astro automatically installed the Tailwind packages, created the Tailwind configuration file in your root directory, and wired it up in `astro.config.mjs`. Tailwind is now ready to use across all your `.astro` files!

### Step 2: Install React

Astro is framework-agnostic. While Astro components are great for static content, you might want to use React for highly interactive components (like a complex form or a state-driven UI).

1. In the same terminal, run:
   ```bash
   npx astro add react
   ```
2. Just like before, confirm all the prompts by pressing Enter *(Yes)*.

**What just happened?**  
Astro installed React, React-DOM, and the Astro-React integration, and updated your `astro.config.mjs` to support React components.

### Step 3: How to Use React and Tailwind Together in Astro

Now that both are installed, let's see how they work together using Astro's Islands Architecture.

#### 1. Create a React Component
Create a new file at `src/components/Button.jsx`. Notice we are using Tailwind classes directly inside the React component!

```jsx
export default function Button() {
  return (
    <button class="bg-blue-500 text-white font-bold py-2 px-4 rounded hover:bg-blue-700">
      Click Me!
    </button>
  );
}
```

#### 2. Import and Use it in an Astro Page
Go to `src/pages/index.astro` and import your new React component.

```astro
---
import Button from '../components/Button.jsx';
---

<h1 class="text-3xl font-bold mb-5">Hello Astro + React + Tailwind</h1>

<!-- Using the React component -->
<Button client:load />
```

### Important: Client Directives (`client:load`)

Did you notice the `client:load` on the `<Button/>` component?  
By default, Astro renders React components as static HTML and sends zero JavaScript to the browser. If your React component has interactivity (like an `onClick` event or `useState`), you must use a client directive to tell Astro to load the JavaScript for that specific component.

* **`client:load`:** Loads the JavaScript immediately when the page loads. *(Good for critical UI)*.
* **`client:visible`:** Loads the JavaScript only when the user scrolls down and sees the component. *(Great for performance!)*.
* **`client:idle`:** Loads the JavaScript only after the rest of the page has finished loading.

## 6. Fetching and Displaying Data (JSON, APIs, and Databases)

Astro is inherently server-first, meaning any data fetching you write happens on the server during build time (for static sites) or at request time (for Server-Side Rendering - SSR). This prevents heavy database queries or API secrets from ever leaking to the client browser.

### 1. Working with Local Data Files (JSON / YAML)

You can import local data files directly into your Astro components using standard ES modules or `import.meta.glob`.

#### Example: Displaying data from a local JSON file

Create a data file at `src/data/products.json`:

```json
[
  { "id": 1, "name": "Wireless Mouse", "price": 29.99, "inStock": true },
  { "id": 2, "name": "Mechanical Keyboard", "price": 89.99, "inStock": false },
  { "id": 3, "name": "4K Monitor", "price": 349.99, "inStock": true }
]
```

Now, import and loop through it in `src/pages/products.astro`:

```astro
---
import productsData from '../data/products.json';
---

<html>
  <head>
    <title>Product List</title>
  </head>
  <body>
    <h1>Our Products</h1>
    <ul>
      {productsData.map((product) => (
        <li>
          <strong>{product.name}</strong> - \${product.price}
          {!product.inStock && <span style="color: red;"> (Out of Stock)</span>}
        </li>
      ))}
    </ul>
  </body>
</html>
```

### 2. Fetching Remote Data (APIs and Databases)

Because Astro frontmatter runs entirely on the server, you can perform asynchronous operations like standard JavaScript native `fetch()` calls or communicate directly with your database drivers (SQL, MongoDB, Prisma, etc.).

#### Example: Fetching from an external REST API or Database

```astro
---
// src/pages/users.astro

// You can use native fetch directly in the frontmatter
const response = await fetch('https://jsonplaceholder.typicode.com/users');
const users = await response.json();

// If using a database, you would query it right here:
// const users = await db.select().from(usersTable);
---

<h1>Users Directory</h1>
<div class="user-grid">
  {users.map((user) => (
    <div class="user-card">
      <h3>{user.name}</h3>
      <p>Email: {user.email}</p>
      <p>Company: {user.company.name}</p>
    </div>
  ))}
</div>
```

---

## 7. Content Collections: Schemas and Document Validation

When dealing with a lot of content files (like Markdown, MDX, JSON, or YAML), managing data manually can lead to broken pages due to typos in frontmatter parameters.

Astro solves this natively using Content Collections. Content Collections treat your files like a local database, enforcing a strict schema with data validation via Zod.

### Step 1: Organize Your Files

Content collections must live inside a specific folder structure under `src/content/`. For example, create a collection folder for blog posts: `src/content/blog/`.

Create a file `src/content/blog/first-post.md`:

```markdown
---
title: "My First Astro Blog Post"
pubDate: 2026-07-06
description: "Learning how to validate schemas in Astro."
author: "Alex"
tags: ["astro", "learning"]
---
This is the main body content of the blog post.
```

### Step 2: Configure the Validation Schema

To enforce what data `first-post.md` must contain, create a configuration file at `src/content/config.ts`.

Astro uses Zod under the hood for easy validation rules:

```typescript
// src/content/config.ts
import { defineCollection, z } from 'astro:content';

// Define the validation rules for the blog collection
const blogCollection = defineCollection({
  type: 'content', // 'content' is for Markdown/MDX, 'data' is for JSON/YAML
  schema: z.object({
    title: z.string(),
    pubDate: z.date(),
    description: z.string().max(160, "Description must be under 160 characters"),
    author: z.string().default("Anonymous"),
    tags: z.array(z.string()),
    isDraft: z.boolean().optional() // Optional parameter
  }),
});

// Export your collections object to register them
export const collections = {
  'blog': blogCollection,
};
```

#### Why is this helpful?
If a developer accidentally sets `pubDate: "yesterday"` (a string instead of a valid date) or completely forgets to add a title, Astro will throw an explicit error in the terminal during development and fail the production build. This prevents broken production pages.

### Step 3: Querying and Displaying Validated Content

Once configured, you can query your safe, validated collections on any page using the `getCollection` function.

Inside `src/pages/blog.astro`:

```astro
---
import { getCollection } from 'astro:content';

// Fetch all entries from the 'blog' collection safely
const allPosts = await getCollection('blog');
---

<html>
<head>
  <title>My Safe Blog</title>
</head>
<body>
  <h1>Blog Posts</h1>
  <div class="posts-list">
    {allPosts.map(post => (
      <article>
        {/* 'data' contains the frontmatter fields checked by Zod */}
        <h2>{post.data.title}</h2>
        <p>Published on: {post.data.pubDate.toLocaleDateString()}</p>
        <p>{post.data.description}</p>
        <a href={`/blog/${post.slug}`}>Read More</a>
      </article>
    ))}
  </div>
</body>
</html>
```
## 8. Adding JavaScript to Astro: Server vs. Client

Astro splits JavaScript execution into two completely distinct worlds: **Server-side (Build-time)** and **Client-side (Browser)**. Understanding this distinction is the key to writing efficient code and mastering Astro's performance benefits.

### 1. Server-Side JavaScript (The Frontmatter)

Any JavaScript written inside the code fences (`---`) at the top of an `.astro` file runs only on the server during the build process (or at request time if using SSR). This code never reaches the user's browser.

#### What is it used for?
* Fetching data from external APIs or databases.
* Importing other components or layouts.
* Defining variables, constants, or functions that will be used to generate the HTML.

#### Example:

```astro
---
// src/components/ServerGreeting.astro

// This runs ONLY on the server. You can see this in your terminal, not the browser console.
const consoleMessage = "Hello from the server frontmatter!";
console.log(consoleMessage); 

const dynamicUser = "Alex";
const currentHour = new Date().getHours();
const greeting = currentHour < 12 ? "Good morning" : "Good evening";
---

<!-- The values calculated above are safely injected into the HTML -->
<div class="greeting-box">
  <h2>{greeting}, {dynamicUser}!</h2>
  <p>This markup was compiled statically on the server.</p>
</div>
```

---

### 2. Client-Side JavaScript (The `<script>` Tag)

If you need JavaScript to run in the user's browser (e.g., tracking a click, opening a modal, or manipulating the DOM dynamically), you must use a standard HTML `<script>` tag.

Astro automatically processes and bundles your `<script>` tags. It optimizes them, minifies the code, and injects them efficiently into the final page.

#### What you need to know:
* **Scoped by default:** Variables declared inside an Astro script do not leak into the global scope.
* **ES Modules supported:** You can use `import` statements inside your script tags to pull in npm packages or external utility functions.

#### Example: Creating a Toggle Button

```astro
---
// Frontmatter can remain empty if no server logic is needed
---

<div class="interactive-card">
  <h3>Interactive Component</h3>
  <p id="status-text">The system is currently: <strong>IDLE</strong></p>
  
  <!-- Standard HTML button -->
  <button id="action-btn">Activate System</button>
</div>

<script>
  // This code runs entirely in the BROWSER.
  // You will see this console.log in the Chrome/Firefox Developer Tools.
  console.log("The interactive script has loaded in the browser!");

  const button = document.getElementById('action-btn');
  const statusText = document.getElementById('status-text');

  if (button && statusText) {
    button.addEventListener('click', () => {
      statusText.innerHTML = "The system is currently: <span style='color: green;'>ACTIVE</span>";
      button.disabled = true;
      button.innerText = "System Engaged";
    });
  }
</script>
```

---

### 3. Summary: When to use which?

| If you want to... | Use Server JS (`---`) | Use Client JS (`<script>`) |
| :--- | :---: | :---: |
| Securely read database credentials or API keys | ✅ Yes | ❌ No *(Security risk!)* |
| Import and arrange components | ✅ Yes | ❌ No |
| Fetch raw data and filter it before building HTML | ✅ Yes | ❌ No *(Waste of bandwidth)* |
| Listen to clicks, scrolls, or form submissions | ❌ No | ✅ Yes |
| Toggle CSS classes dynamically based on user interaction | ❌ No | ✅ Yes |

#### Advanced Trick: The `is:inline` Directive

If you want Astro to completely ignore a script tag and leave it exactly as it is without bundling, processing, or scoping it, you can add the `is:inline` directive:

```html
<script is:inline>
  // This script will be copied verbatim into the final HTML output.
  // Useful for third-party analytics scripts (like Google Analytics).
  console.log("I am an un-processed, global script!");
</script>
```
## 9. Dynamic Pages and Routing

Astro uses file-based routing, which means any file in `src/pages/` becomes a URL. But what if you have hundreds of blog posts or products? You don't want to create hundreds of static `.astro` files manually. That's where Dynamic Routing comes in.

To create a dynamic route, you wrap the filename in brackets, like `[slug].astro` or `[id].astro`.

### Strategy A: Static Site Generation Mode (SSG - Default)

If your site is static, Astro needs to know exactly what pages exist at build time. You must export a `getStaticPaths()` function that returns an array of all the paths you want to generate.

Create a file at `src/pages/posts/[slug].astro`:

```astro
---
// src/pages/posts/[slug].astro

export async function getStaticPaths() {
  // This could also come from an API fetch or Content Collection
  return [
    { params: { slug: 'intro-to-astro' }, props: { title: 'Welcome to Astro!' } },
    { params: { slug: 'tailwind-tips' }, props: { title: 'Mastering Tailwind' } },
  ];
}

// Retrieve the parameters and props for the current URL
const { slug } = Astro.params;
const { title } = Astro.props;
---

<html>
  <head>
    <title>{title}</title>
  </head>
  <body>
    <h1>{title}</h1>
    <p>You are viewing the post with the slug: <strong>{slug}</strong></p>
  </body>
</html>
```

### Strategy B: Server-Side Rendering Mode (SSR)

If you switch your Astro project to SSR mode (configured in `astro.config.mjs`), you don't need `getStaticPaths()`. Astro will build the page on-demand on the server every time a user visits the URL.

```astro
---
// src/pages/products/[id].astro (In SSR Mode)
const { id } = Astro.params;

// Fetch product details on-the-fly from a database or API
const response = await fetch(`https://api.example.com/products/${id}`);
const product = await response.json();
---

<h1>{product.name}</h1>
<p>{product.description}</p>
```

---

## 10. Understanding "Reactivity" in Astro

Because Astro generates plain HTML by default, pages are not reactive out of the box like a standard React Single Page Application (SPA). If you want real-time reactivity (like updating UI instantly when user data changes), you have two distinct paths:

1. **The Vanilla JS Route (Simple Interactivity):** For minor reactive tricks, use a `<script>` tag to update the DOM manually based on browser events.
2. **The Framework Island Route (Complex Reactivity):** For true application-like reactivity (global state, complex forms, real-time dashboards), you should build that specific section as a React, Vue, or Svelte component and pass data into it using standard props.

```astro
---
import ReactiveCounter from '../components/ReactiveCounter.jsx';
const serverData = { initialCount: 5 };
---

<!-- Passing static server data down into a highly reactive React component -->
<ReactiveCounter initial={serverData.initialCount} client:load />
```

---

## 11. Smooth Page Transitions and Animations

Astro includes built-in support for the browser's native View Transitions API. This allows you to create seamless app-like transitions (like cross-fades, sliding headers, or morphing images) when navigating between completely different HTML pages, without the bloat of a massive client-side JavaScript router.

### Step 1: Enable the Client Router

To activate animations across your entire site, import and place the `<ClientRouter />` component (formerly called `<ViewTransitions />` in older versions) inside the `<head>` of your main Layout file.

```astro
---
// src/layouts/Layout.astro
import { ClientRouter } from 'astro:transitions';
---

<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>My Animated Site</title>
    <!-- This tag converts standard navigation into smooth animations -->
    <ClientRouter />
  </head>
  <body>
    <slot /> <!-- Your page content goes here -->
  </body>
</html>
```

### Step 2: Applying Animation Directives

Once the router is active, you can control exactly how elements behave during navigation using the `transition:animate` directive. Astro gives you four default behaviors out of the box:

* `fade`: (Default) Cross-fade effect.
* `slide`: Left-to-right sliding animation.
* `none`: Disables transition for that specific element.
* `initial`: Resets to browser default fallback.

```html
<!-- This header will slide smoothly when switching pages -->
<h1 transition:animate="slide">Main Dashboard</h1>

<!-- The rest of the content will subtly cross-fade -->
<main transition:animate="fade">
  <p>Welcome to the dashboard page.</p>
</main>
```

### Advanced: Morphing Layout Elements (`transition:name`)

You can tie elements on different pages together so they look like they are physically moving or expanding from one page to another. This is incredibly popular for thumbnail-to-full-image layouts.

On your **List Page** (`index.astro`):

```html
<a href="/products/shoes">
  <img src="/images/shoes.jpg" transition:name="product-image-shoes" />
</a>
```

On your **Detail Page** (`[id].astro`):

```html
<div class="hero-container">
  <!-- Astro will morph the small thumbnail into this big hero image instantly -->
  <img src="/images/shoes.jpg" transition:name="product-image-shoes" class="w-full h-96" />
</div>
```

Astro maps the unique strings in `transition:name` together across navigations and automatically calculates the smooth resizing animation for you.

## 12. Environment Variables in Astro

Environment variables are crucial for storing sensitive data (like database passwords or API private keys) and for changing configuration values based on whether your site is running locally in development, staging, or live in production. 

Astro loads these variables from `.env` files at the root of your project and provides a modern, fully type-safe system (`astro:env`) to validate and access them.

### 1. Where and How to Define Environment Variables

You define your variables inside environment files at the very root of your project directory. Create a file named `.env` for your local development setup:

```env
# .env (Put this file in your project's root folder)

# A public variable that can be exposed to the client browser
PUBLIC_API_URL=https://api.example.com

# A secret variable that must ONLY remain on your backend server
DATABASE_PASSWORD=super_secret_password_123

# A number variable for configuration
ANALYTICS_ID=98765
```

> ⚠️ **Security Warning:** Never commit your `.env` file to Git or upload it to GitHub. Ensure `.env` is listed inside your `.gitignore` file.

### 2. Defining a Type-Safe Schema (Recommended)

To prevent your application from breaking due to missing variables or incorrect data types, Astro allows you to define a schema directly in your configuration file.

Open `astro.config.mjs` and configure your variables using the `envField` utility:

```javascript
// astro.config.mjs
import { defineConfig, envField } from 'astro/config';

export default defineConfig({
  env: {
    schema: {
      PUBLIC_API_URL: envField.string({ 
        context: "client", 
        access: "public" 
      }),
      DATABASE_PASSWORD: envField.string({ 
        context: "server", 
        access: "secret" 
      }),
      ANALYTICS_ID: envField.number({ 
        context: "server", 
        access: "public",
        default: 11111 
      }),
    }
  }
});
```

By configuring this schema, Astro does two amazing things:
* **Automatic Validation:** If you forget to add `DATABASE_PASSWORD` or if `ANALYTICS_ID` is not a number, Astro will immediately throw an error and refuse to build the site, saving you from a broken deployment.
* **Strict Context Enforcement:** If you accidentally try to load a server context variable inside code meant for the user's browser, Astro flags it immediately.

### 3. How to Use Environment Variables (Examples)

Depending on your configured schema, you import your variables from separate subdirectories (`astro:env/client` or `astro:env/server`) to maintain strict separation of concerns.

#### Example A: Using Public Variables on the Client Side
Because `PUBLIC_API_URL` was defined with a client context, you can safely import it into browser scripts or client-side components.

```astro
---
// src/components/Tracker.astro
import { PUBLIC_API_URL } from 'astro:env/client';
---

<div class="tracker-box" data-url={PUBLIC_API_URL}>
  <p>Connecting to external system API...</p>
</div>

<script>
  import { PUBLIC_API_URL } from 'astro:env/client';
  
  // You can safely use it inside browser scripts!
  console.log("Client API target destination:", PUBLIC_API_URL);
</script>
```

#### Example B: Using Secret Variables on the Server Side
Because `DATABASE_PASSWORD` was marked with a server context and secret access, it can only be imported in code running on your server backend.

```astro
---
// src/pages/dashboard.astro
import { DATABASE_PASSWORD } from 'astro:env/server';

// This code is executed completely on the server side
// The secret password will NEVER be included in the browser bundle
const connectionStatus = DATABASE_PASSWORD ? "Connected Successfully" : "Connection Failed";
---

<html>
  <body>
    <h1>Internal Management Console</h1>
    <!-- It is safe to show the status, but never render the password text itself! -->
    <p>Database Status: {connectionStatus}</p>
  </body>
</html>
```

### 4. Alternative Method: Vite's Classic `import.meta.env`

If you are not using a schema configuration or you want to pull default environment variables provided out-of-the-box by Vite, you can use the classic `import.meta.env` object.

Astro provides these default variables built-in:
* `import.meta.env.MODE`: The mode your site is currently running in (development or production).
* `import.meta.env.PROD`: Evaluates to `true` if your site is compiled and running in production.
* `import.meta.env.DEV`: Evaluates to `true` if your site is running locally in development.

#### Usage Example:

```astro
---
// src/components/Analytics.astro

const isProduction = import.meta.env.PROD;
---

{isProduction ? (
  <!-- Real tracking script injected only when live -->
  <script is:inline src="https://analytics.example.com/track.js"></script>
) : (
  <!-- Dev mode fallback notice -->
  <p class="dev-notice">Analytics disabled during local testing.</p>
)}
```
## 13. On-Demand Server Rendering (SSR & Hybrid Rendering)

By default, Astro acts as a Static Site Generation (SSG) engine. This means when you run `npm run build`, Astro takes all your data, fetches your APIs, and freezes them into permanent, unchangeable HTML files. If a variable changes later, your users won't see it until you rebuild and redeploy the entire site.

If you want certain pages or values to fluctuate dynamically on every single page refresh (like live sports scores, user dashboards, shopping carts, or randomized numbers), you must enable Server-Side Rendering (SSR) or Hybrid Rendering.

### Step 1: Configure Your Project Output Mode

To stop Astro from freezing your pages at build time, you need to open your `astro.config.mjs` file and add the `output` property. You have two professional choices:

#### Choice A: Hybrid Mode (Highly Recommended)
Everything in your project stays static (super fast) by default. You explicitly choose which specific pages will render dynamically on the server.

```javascript
// astro.config.mjs
import { defineConfig } from 'astro/config';

export default defineConfig({
  output: 'hybrid', // Everything is static by default, but allows on-demand rendering
});
```

#### Choice B: Server Mode
The opposite of hybrid. Every single page on your website will be calculated on-the-fly by a server on every request.

```javascript
// astro.config.mjs
import { defineConfig } from 'astro/config';

export default defineConfig({
  output: 'server', // Everything runs on a live server by default
});
```

---

### Step 2: Making a Page Dynamic (Using Hybrid Mode)

If you chose `output: 'hybrid'`, your pages are still static. To tell Astro *"Do not freeze this page during the build process, compile it live when the user visits it"*, you must export a special `prerender` variable at the very top of your page frontmatter.

Create a dynamic file at `src/pages/live-score.astro`:

```astro
---
// src/pages/live-score.astro

// 1. Tell Astro NOT to pre-render this page into a static file
export const prerender = false;

// 2. This code runs on the server EXACTLY when the user hits Enter on the URL
const requestTime = new Date().toLocaleTimeString();
const serverRandomValue = Math.floor(Math.random() * 100);

// You can also access live cookies or request headers here:
const userAgent = Astro.request.headers.get('user-agent') || 'Unknown Browser';
---

<html>
  <head>
    <title>Live Server Variables</title>
  </head>
  <body style="font-family: sans-serif; padding: 2rem; background: #0f172a; color: white;">
    
    <h1>Dynamic Server Dashboard</h1>
    <p>This page is recalculated by the server on every single refresh.</p>
    
    <hr style="border-color: #334155;" />

    <ul>
      <li><strong>Current Server Time:</strong> {requestTime}</li>
      <li><strong>Live Dynamic Score:</strong> {serverRandomValue} points</li>
      <li><strong>Your Browser info:</strong> {userAgent}</li>
    </ul>

    <button onclick="window.location.reload()" style="padding: 0.5rem 1rem; background: #3b82f6; color: white; border: none; border-radius: 4px; cursor: pointer;">
      Refresh Page to Change Variables
    </button>

  </body>
</html>
```

#### How it works behind the scenes:
* When you run `npm run build`, Astro will skip creating an ordinary HTML file for `live-score.astro`.
* Instead, it bundles this page into a server-side function.
* Every time a user visits `/live-score`, the server executes that function, generates a fresh `Math.random()` and the exact current time, and drops the raw HTML directly into the user's hands.

---

### Step 3: Crucial Requirement (Adapters)

Because your project now needs to run real server code dynamically on-demand, standard static hosting platforms (like basic GitHub Pages) will not work. You need a platform running a live environment (Node.js, Serverless Functions, or Edge Networks).

You must install a deployment **Adapter** so Astro knows how to compile your server code for your target hosting provider (e.g., Vercel, Netlify, Cloudflare, or an independent Node.js server).

For example, to deploy your dynamic project to Vercel, you simply run:

```bash
npx astro add vercel
```

Astro will automatically install the necessary packages and update your configuration file to connect your on-demand server rendering code perfectly:

```javascript
// astro.config.mjs (Updated automatically by the command)
import { defineConfig } from 'astro/config';
import vercel from '@astrojs/vercel';

export default defineConfig({
  output: 'hybrid',
  adapter: vercel(), // Connects the server logic smoothly to Vercel's network
});
```
## 14. Deploying Your Astro Website

Once your website is ready, it is time to publish it to the internet. Because Astro is incredibly versatile, you can deploy it as a purely static site for free on almost any platform, or as a dynamic server-side site using cheap or free edge-computing networks.

Here are step-by-step guides for two of the best and most popular free deployment options: **GitHub Pages** (for static sites) and **Deno Deploy** (for static, hybrid, or SSR sites).

---

### Option A: Deploying to GitHub Pages (Static / SSG)

GitHub Pages is 100% free and perfect if your project is configured as a standard static website (`output: 'static'` or default configuration).

#### Step 1: Update your Astro Configuration
GitHub Pages requires Astro to know the final URL of your website so it can build internal links properly. Open your `astro.config.mjs` and add your deployment URL:

```javascript
// astro.config.mjs
import { defineConfig } from 'astro/config';

export default defineConfig({
  // Replace with your GitHub username and repository name
  site: 'https://your-username.github.io/your-repo-name',
});
```

#### Step 2: Create a GitHub Actions Workflow
Instead of building the site manually and uploading files, we will set up an automated script (GitHub Actions) that builds and deploys your site every time you push code to GitHub.

At the root of your project, create a nested folder structure: `.github/workflows/` and inside it, create a file named `deploy.yml`. Paste the following standard configuration:

```yaml
# .github/workflows/deploy.yml
name: Deploy to GitHub Pages

on:
  push:
    branches: [ main ] # Triggers the script when you push to the main branch

permissions:
  contents: read
  pages: write
  id-token: write

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Setup Node.js
        uses: actions/setup-node@v4
        with:
          node-version: 20
          cache: npm

      - name: Install dependencies
        run: npm ci

      - name: Build Astro Website
        run: npm run build

      - name: Upload production artifacts
        uses: actions/upload-pages-artifact@v3
        with:
          path: ./dist # Astro's default output directory

  deploy:
    needs: build
    runs-on: ubuntu-latest
    environment:
      name: github-pages
      url: \${{ steps.deployment.outputs.page_url }}
    steps:
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

#### Step 3: Configure Repository Settings on GitHub
1. Commit your changes and push your project to a public repository on GitHub.
2. Go to your repository on the GitHub website.
3. Click on **Settings** -> **Pages** (in the left sidebar).
4. Under *Build and deployment*, look for *Source* and switch the dropdown menu from **Deploy from a branch** to **GitHub Actions**.

The moment you switch this, your automation script will fire up. You can watch the progress in the **Actions** tab. Once completed, your site will be live!

---

### Option B: Deploying to Deno Deploy (Static, Hybrid, or SSR)

Deno Deploy is a modern, ultra-fast global edge network. It has an excellent free tier and can host standard static Astro sites as well as on-demand server-rendered sites (`output: 'hybrid'` or `output: 'server'`).

#### Step 1: Install the Deno Adapter
If your project uses server-side features, Astro needs to know how to bundle them specifically for Deno's environment. Run this automated setup command in your terminal:

```bash
npx astro add deno
```

Confirm all prompts with **Enter**. This command will install `@astrojs/deno` and automatically update your `astro.config.mjs` file to look like this:

```javascript
// astro.config.mjs
import { defineConfig } from 'astro/config';
import deno from '@astrojs/deno';

export default defineConfig({
  output: 'hybrid', // Or 'server' / 'static'
  adapter: deno(),   // Plugs Deno into the build loop
});
```

#### Step 2: Push Your Code to GitHub
Ensure all your project files (including the updated configuration) are committed and pushed to your GitHub repository.

#### Step 3: Connect to Deno Deploy
1. Go to [dash.deno.com](https://deno.com) and create a free account (you can sign in instantly using your GitHub account).
2. Click on **New Project**.
3. Select **Connect a GitHub repository**.
4. Choose your organization, your Astro project repository, and select the `main` branch.
5. Under *Build Settings*, Deno Deploy will automatically detect that you are using Astro! It will autofill the framework settings for you:
   * **Framework:** Astro
   * **Build Command:** `npm run build`
   * **Output Directory:** `dist/` (for static) or it will detect the server entrypoint automatically.
6. Click **Link** or **Deploy Project**.

Deno Deploy will pull your repository, execute your build script on their servers, and distribute your website globally across their edge infrastructure in just a few seconds. You will receive a production-ready URL instantly.

## 15. Server Islands in Astro

Server Islands are a revolutionary architectural primitive introduced by Astro. They solve one of the oldest dilemmas in web development: choosing between the extreme performance of Static Site Generation (SSG) and the extreme personalization of Server-Side Rendering (SSR).

With Server Islands, you don't have to choose. You can cache your entire website layout on a global CDN so it loads instantly, while letting specific, highly dynamic areas load in the background directly from a server.

### 1. The Problem Server Islands Solve

Imagine an e-commerce product page. 95% of the page—the layout, title, description, and product images—is exactly the same for every single user. This means it can be safely pre-rendered and cached at the edge for instant loading.

However, a tiny 5% of that page needs to be dynamic or personalized:
* A personalized profile navigation avatar (`src/components/Avatar.astro`).
* Real-time warehouse stock counts.
* Unique, user-targeted product recommendations.

Previously, you had to either convert the entire page to SSR (slowing down the initial load time) or use client-side JavaScript to fetch the data from an API (showing ugly, blank layout shifts).

Astro Server Islands deliver a static page shell immediately, leaving "slots" for the server to render and stream those dynamic parts individually as soon as they are computed.

### 2. How Server Islands Work Behind the Scenes

* **At Build Time:** Astro compiles the bulk of your page as static HTML. Wherever it finds a server-deferred component, it omits the code and replaces it with a lightweight script and any optional fallback HTML you provided.
* **On Page Load:** The user requests your page and receives the CDN-cached layout instantly.
* **The Swap:** The injected background script fires an asynchronous request to an isolated, automated server route created by Astro specifically for that component. The server calculates the code, generates the fresh HTML, and streams it back to the browser, replacing the placeholder seamlessly.

---

### 3. Step-by-Step Implementation

To use Server Islands, your project must be configured to support on-demand rendering.

#### Step 1: Ensure an Adapter is Configured
Ensure you have set your project's output to hybrid or server and connected an adapter (like Node, Vercel, or Netlify) inside your configuration file.

```javascript
// astro.config.mjs
import { defineConfig } from 'astro/config';
import vercel from '@astrojs/vercel';

export default defineConfig({
  output: 'hybrid', // Allows selective server-rendering
  adapter: vercel(),
});
```

#### Step 2: Create a Server Component
Create a standard Astro component that performs dynamic server actions, such as checking user session cookies or pulling data on-demand.

```astro
---
// src/components/PersonalizedWelcome.astro

// This component checks cookies directly on the server
const userSession = Astro.cookies.get('user_session')?.value;

let userName = "Guest";

if (userSession) {
  // Simulating an internal database look-up based on a session token
  const response = await fetch(`https://api.internal/user?token=${userSession}`);
  const userData = await response.json();
  userName = userData.name;
}
---

<div class="user-profile-badge">
  <span>Welcome back, <strong>{userName}</strong>!</span>
</div>
```

#### Step 3: Apply the `server:defer` Directive
Go to any page (like your homepage `src/pages/index.astro`), import your component, and turn it into a Server Island by appending the `server:defer` directive.

You can also pass a child element with the `slot="fallback"` attribute to display placeholder loading states (like skeletons or spinners) while the server computes the content.

```astro
---
// src/pages/index.astro
import PersonalizedWelcome from '../components/PersonalizedWelcome.astro';
---

<html>
  <head>
    <title>E-Commerce Home</title>
  </head>
  <body class="bg-white text-black">
    
    <nav class="flex justify-between p-4 bg-slate-100">
      <span class="font-bold">MyStore</span>
      
      <!-- SERVER ISLAND APPLIED HERE -->
      <PersonalizedWelcome server:defer>
        <!-- This placeholder fallback displays instantly while the server loads the true data -->
        <div slot="fallback" class="animate-pulse bg-slate-200 h-6 w-32 rounded"></div>
      </PersonalizedWelcome>
    </nav>

    <main class="p-10">
      <h1>Flash Sale Products</h1>
      <p>This entire static section is aggressively cached globally via CDN for maximum speed.</p>
    </main>

  </body>
</html>
```

---

### 4. Important Rules and Limitations

* **Astro Components Only:** The `server:defer` directive works strictly on native `.astro` components. You cannot place it directly on a React or Vue component (though your `.astro` server island can import and render framework components internally).
* **Placement Restrictions:** You cannot place a Server Island directly inside a client-hydrated UI component (like a React island using `client:load`), but you can pass a Server Island component down as a child.
* **Serialization Limits:** Because props passed to a Server Island are sent across a network request under the hood, you can only pass serializable data types (strings, numbers, booleans, simple objects/arrays). You cannot pass complex JavaScript functions or circular references.
* **Isolated Scope:** Server Islands execute outside the context of the parent page request. Properties like `Astro.url` or `Astro.request.url` inside the server island will point to the custom fallback endpoint route (`/_server-islands/...`) instead of the browser's current page URL. To determine what page requested the island, parse the Referer header:
  ```javascript
  const referer = Astro.request.headers.get('referer');
  ```
