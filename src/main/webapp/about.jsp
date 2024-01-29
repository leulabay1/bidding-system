<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>About Us - Your Bidding System</title>
    <!-- Include the Tailwind CSS CDN or install it using your preferred method -->
    <script src="https://cdn.tailwindcss.com"></script>
  </head>
  <body class="bg-gray-100">
    <!-- Navigation Bar -->
    <section class="background-radial-gradient overflow-hidden mb-20">
      <style>
        .background-radial-gradient {
          background-color: hsl(218, 41%, 15%);
          background-image: radial-gradient(
              650px circle at 0% 0%,
              hsl(218, 41%, 35%) 15%,
              hsl(218, 41%, 30%) 35%,
              hsl(218, 41%, 20%) 75%,
              hsl(218, 40%, 19%) 80%,
              transparent 100%
            ),
            radial-gradient(
              1250px circle at 100% 100%,
              hsl(218, 41%, 45%) 15%,
              hsl(218, 41%, 30%) 35%,
              hsl(218, 41%, 20%) 75%,
              hsl(218, 41%, 19%) 80%,
              transparent 100%
            );
        }
        .background-radial-gradient2 {
          background-color: hsl(218, 41%, 15%);
          color: #fdfdfd;
          background-image: radial-gradient(
              100px circle at 0% 0%,
              hsl(39, 100%, 50%) 15%,
              hsl(39, 100%, 30%) 35%,
              hsl(39, 100%, 20%) 75%,
              hsl(39, 100%, 19%) 80%,
              transparent 100%
            ),
            radial-gradient(
              200px circle at 100% 100%,
              hsl(39, 100%, 50%) 15%,
              hsl(39, 100%, 30%) 35%,
              hsl(39, 100%, 20%) 75%,
              hsl(39, 100%, 19%) 80%,
              transparent 100%
            );
        }

        #radius-shape-1 {
          height: 220px;
          width: 220px;
          top: -60px;
          left: -130px;
          background: radial-gradient(#fda40070, #fda400);
          overflow: hidden;
        }

        #radius-shape-2 {
          border-radius: 38% 62% 63% 37% / 70% 33% 67% 30%;
          bottom: -60px;
          right: -110px;
          width: 300px;
          height: 300px;
          background: radial-gradient(#fda40070, #fda400);
          overflow: hidden;
        }
      </style>
      <!-- Navbar -->
     <%@include file="header.jsp"%>
      <!-- Navbar -->

      <!-- About Us Hero Section with Image on Left -->
      <section
        class="container mx-auto max-w-7xl py-2 px-2 sm:px-6 lg:px-8 my-8 flex flex-col-reverse lg:flex-row items-center gap-2 min-h-[70vh] justify-center"
      >
        <div class="w-3/4 lg:w-1/2 lg:pr-8">
          <img
            class="w-full h-auto rounded"
            src="image/about.jpg"
            alt="About Us Image"
          />
        </div>
        <div class="w-full lg:w-1/2 px-4 text-center lg:text-left">
          <div class="flex flex-col gap-4 text-white">
            <h1 class="text-4xl lg:text-5xl font-semibold mb-4 text-[#FDA400]">
              Welcome to SwiftBid
            </h1>
            <p class="text-lg">
              Transforming online bidding with innovation and transparency
            </p>
            <p class="text-gray-200">
              Welcome to SwiftBid, where we are passionate about transforming
              the way people engage in online bidding. Our platform is designed
              to provide a seamless and transparent experience for both buyers
              and sellers.
            </p>
          </div>
        </div>
      </section>
    </section>
    <!-- About Us Section -->
    <section class="">
      <!-- Our Team -->
      <div
        class="mx-auto max-w-7xl py-2 px-2 sm:px-6 lg:px-8 mb-8 flex flex-col gap-5 justify-center items-center min-h-[50vh]"
      >
        <h2 class="text-4xl font-bold mb-4">Our Team</h2>
        <p class="text-gray-700 text-center max-w-[500px] w-[90%] mx-auto">
          Behind SwiftBid is a dedicated team of professionals who are committed
          to innovation, transparency, and creating a vibrant community for our
          users. Meet the individuals who drive our vision forward.
        </p>
        <div class="grid gap-x-6 mt-20 md:grid-cols-3 lg:gap-x-12">
          <div class="mb-24 md:mb-0">
            <div
              class="block h-full rounded-lg bg-[#f7c870] shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)]"
            >
              <div class="flex justify-center">
                <div class="flex justify-center -mt-[75px]">
                  <img
                    src="image/leul.jpg"
                    class="mx-auto rounded-full shadow-lg w-[150px] h-[150px] object-cover"
                    alt="Avatar"
                  />
                </div>
              </div>
              <div class="p-6">
                <h5 class="mb-4 text-lg font-bold">Leul Abay</h5>
                <p class="mb-6">Full-stack developer</p>
                <ul class="mx-auto flex list-inside justify-center">
                  <a href="#!" class="px-2">
                    <!-- GitHub -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-4 w-4 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"
                      />
                    </svg>
                  </a>
                  <a href="#!" class="px-2">
                    <!-- Twitter -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-4 w-4 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"
                      />
                    </svg>
                  </a>
                  <a href="#!" class="px-2">
                    <!-- Linkedin -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-3.5 w-3.5 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M4.98 3.5c0 1.381-1.11 2.5-2.48 2.5s-2.48-1.119-2.48-2.5c0-1.38 1.11-2.5 2.48-2.5s2.48 1.12 2.48 2.5zm.02 4.5h-5v16h5v-16zm7.982 0h-4.968v16h4.969v-8.399c0-4.67 6.029-5.052 6.029 0v8.399h4.988v-10.131c0-7.88-8.922-7.593-11.018-3.714v-2.155z"
                      />
                    </svg>
                  </a>
                </ul>
              </div>
            </div>
          </div>

          <div class="mb-24 md:mb-0">
            <div
              class="block h-full rounded-lg bg-[#f7c870] shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)]"
            >
              <div class="flex justify-center">
                <div class="flex justify-center -mt-[75px]">
                  <img
                    src="image/abiy.jpg"
                    class="mx-auto rounded-full shadow-lg w-[150px] h-[150px] object-cover"
                    alt="Avatar"
                  />
                </div>
              </div>
              <div class="p-6">
                <h5 class="mb-4 text-lg font-bold">Abiy Biru</h5>
                <p class="mb-6">Full-stack developer</p>
                <ul class="mx-auto flex list-inside justify-center">
                  <a href="#!" class="px-2">
                    <!-- Facebook -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      class="h-4 w-4 text-primary"
                      fill="currentColor"
                      viewBox="0 0 24 24"
                    >
                      <path
                        d="M9 8h-3v4h3v12h5v-12h3.642l.358-4h-4v-1.667c0-.955.192-1.333 1.115-1.333h2.885v-5h-3.808c-3.596 0-5.192 1.583-5.192 4.615v3.385z"
                      />
                    </svg>
                  </a>
                  <a href="#!" class="px-2">
                    <!-- Twitter -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-4 w-4 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"
                      />
                    </svg>
                  </a>
                  <a href="#!" class="px-2">
                    <!-- Linkedin -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-3.5 w-3.5 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M4.98 3.5c0 1.381-1.11 2.5-2.48 2.5s-2.48-1.119-2.48-2.5c0-1.38 1.11-2.5 2.48-2.5s2.48 1.12 2.48 2.5zm.02 4.5h-5v16h5v-16zm7.982 0h-4.968v16h4.969v-8.399c0-4.67 6.029-5.052 6.029 0v8.399h4.988v-10.131c0-7.88-8.922-7.593-11.018-3.714v-2.155z"
                      />
                    </svg>
                  </a>
                </ul>
              </div>
            </div>
          </div>

          <div class="">
            <div
              class="block h-full rounded-lg bg-[#f7c870] shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)]"
            >
              <div class="flex justify-center">
                <div class="flex justify-center -mt-[75px]">
                  <img
                    src="image/michael.jpg"
                    class="mx-auto rounded-full shadow-lg w-[150px] h-[150px] object-cover"
                    alt="Avatar"
                  />
                </div>
              </div>
              <div class="p-6">
                <h5 class="mb-4 text-lg font-bold">Michael Gashawtena</h5>
                <p class="mb-6">Frontend developer</p>
                <ul class="mx-auto flex list-inside justify-center">
                  <a href="#!" class="px-2">
                    <!-- Dribbble -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-4 w-4 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M12 0c-6.628 0-12 5.373-12 12s5.372 12 12 12 12-5.373 12-12-5.372-12-12-12zm9.885 11.441c-2.575-.422-4.943-.445-7.103-.073-.244-.563-.497-1.125-.767-1.68 2.31-1 4.165-2.358 5.548-4.082 1.35 1.594 2.197 3.619 2.322 5.835zm-3.842-7.282c-1.205 1.554-2.868 2.783-4.986 3.68-1.016-1.861-2.178-3.676-3.488-5.438.779-.197 1.591-.314 2.431-.314 2.275 0 4.368.779 6.043 2.072zm-10.516-.993c1.331 1.742 2.511 3.538 3.537 5.381-2.43.715-5.331 1.082-8.684 1.105.692-2.835 2.601-5.193 5.147-6.486zm-5.44 8.834l.013-.256c3.849-.005 7.169-.448 9.95-1.322.233.475.456.952.67 1.432-3.38 1.057-6.165 3.222-8.337 6.48-1.432-1.719-2.296-3.927-2.296-6.334zm3.829 7.81c1.969-3.088 4.482-5.098 7.598-6.027.928 2.42 1.609 4.91 2.043 7.46-3.349 1.291-6.953.666-9.641-1.433zm11.586.43c-.438-2.353-1.08-4.653-1.92-6.897 1.876-.265 3.94-.196 6.199.196-.437 2.786-2.028 5.192-4.279 6.701z"
                      />
                    </svg>
                  </a>
                  <a href="#!" class="px-2">
                    <!-- Linkedin -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-3.5 w-3.5 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M4.98 3.5c0 1.381-1.11 2.5-2.48 2.5s-2.48-1.119-2.48-2.5c0-1.38 1.11-2.5 2.48-2.5s2.48 1.12 2.48 2.5zm.02 4.5h-5v16h5v-16zm7.982 0h-4.968v16h4.969v-8.399c0-4.67 6.029-5.052 6.029 0v8.399h4.988v-10.131c0-7.88-8.922-7.593-11.018-3.714v-2.155z"
                      />
                    </svg>
                  </a>
                  <a href="#!" class="px-2">
                    <!-- Instagram -->
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 24 24"
                      class="h-4 w-4 text-primary"
                    >
                      <path
                        fill="currentColor"
                        d="M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z"
                      />
                    </svg>
                  </a>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <!-- Add team member details or images as needed -->
      </div>

      <!-- Mission and Values -->
      <div
        class="mx-auto max-w-7xl py-2 px-2 sm:px-6 lg:px-8 bg-gray-300 mb-8 flex flex-col gap-5 justify-center items-center min-h-[50vh]"
      >
        <i
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            height="40"
            width="40"
            viewBox="0 0 512 512"
          >
            <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
            <path
              fill="#fda400"
              d="M448 256A192 192 0 1 0 64 256a192 192 0 1 0 384 0zM0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256zm256 80a80 80 0 1 0 0-160 80 80 0 1 0 0 160zm0-224a144 144 0 1 1 0 288 144 144 0 1 1 0-288zM224 256a32 32 0 1 1 64 0 32 32 0 1 1 -64 0z"
            />
          </svg>
        </i>
        <h2 class="text-4xl font-bold mb-4">Mission and Values</h2>
        <p class="text-gray-700 text-center max-w-[500px] w-[90%] mx-auto">
          Our mission is to revolutionize the online bidding experience by
          providing a platform that empowers users, fosters fair competition,
          and ensures transparency. We believe in building a community where
          connections are forged, relationships are built, and collaborations
          flourish.
        </p>
        <p class="text-gray-700 text-center max-w-[500px] w-[90%] mx-auto">
          Our core values include empowerment, innovation, transparency,
          community building, and environmental responsibility. These values
          guide every aspect of our business and reflect our commitment to
          creating a positive impact in the online bidding industry.
        </p>
      </div>

      <!-- Contact Us -->
      <div
        class="mx-auto max-w-7xl py-2 px-2 sm:px-6 lg:px-8 mb-8 flex flex-col gap-5 justify-center items-center min-h-[50vh]"
      >
        <h2 class="text-4xl font-bold mb-4 flex items-center gap-3">
          <span>Contact Us</span>
          <i
            ><svg
              xmlns="http://www.w3.org/2000/svg"
              height="32"
              width="32"
              viewBox="0 0 512 512"
            >
              <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
              <path
                fill="#fda400"
                d="M48 64C21.5 64 0 85.5 0 112c0 15.1 7.1 29.3 19.2 38.4L236.8 313.6c11.4 8.5 27 8.5 38.4 0L492.8 150.4c12.1-9.1 19.2-23.3 19.2-38.4c0-26.5-21.5-48-48-48H48zM0 176V384c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V176L294.4 339.2c-22.8 17.1-54 17.1-76.8 0L0 176z"
              />
            </svg>
          </i>
        </h2>
        <p class="text-gray-700 text-center max-w-[500px] w-[90%] mx-auto">
          Have questions or want to get in touch with us? Reach out to our team
          at
          <a href="mailto:info@SwiftBid.com" class="text-blue-500"
            >info@SwiftBid.com</a
          >
          We're here to assist you!
        </p>
      </div>
    </section>

    <!-- Footer container -->
    <footer class="bg-[#1d2b44] text-center text-[#FBFBFD] lg:text-left">
      <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <div
          class="flex items-center justify-center border-b-2 border-neutral-200 p-6 lg:justify-between"
        >
          <div class="mr-12 hidden lg:block">
            <span>Get connected with us on social networks:</span>
          </div>
          <!-- Social network icons container -->
          <div class="flex justify-center">
            <a href="#!" class="mr-6 text-[#FBFBFD]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-4 w-4"
                fill="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  d="M9 8h-3v4h3v12h5v-12h3.642l.358-4h-4v-1.667c0-.955.192-1.333 1.115-1.333h2.885v-5h-3.808c-3.596 0-5.192 1.583-5.192 4.615v3.385z"
                />
              </svg>
            </a>
            <a href="#!" class="mr-6 text-[#FBFBFD]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-4 w-4"
                fill="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z"
                />
              </svg>
            </a>
            <a href="#!" class="mr-6 text-[#FBFBFD]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-5 w-5"
                fill="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  d="M7 11v2.4h3.97c-.16 1.029-1.2 3.02-3.97 3.02-2.39 0-4.34-1.979-4.34-4.42 0-2.44 1.95-4.42 4.34-4.42 1.36 0 2.27.58 2.79 1.08l1.9-1.83c-1.22-1.14-2.8-1.83-4.69-1.83-3.87 0-7 3.13-7 7s3.13 7 7 7c4.04 0 6.721-2.84 6.721-6.84 0-.46-.051-.81-.111-1.16h-6.61zm0 0 17 2h-3v3h-2v-3h-3v-2h3v-3h2v3h3v2z"
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                />
              </svg>
            </a>
            <a href="#!" class="mr-6 text-[#FBFBFD]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-4 w-4"
                fill="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  d="M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z"
                />
              </svg>
            </a>
            <a href="#!" class="mr-6 text-[#FBFBFD]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-4 w-4"
                fill="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  d="M4.98 3.5c0 1.381-1.11 2.5-2.48 2.5s-2.48-1.119-2.48-2.5c0-1.38 1.11-2.5 2.48-2.5s2.48 1.12 2.48 2.5zm.02 4.5h-5v16h5v-16zm7.982 0h-4.968v16h4.969v-8.399c0-4.67 6.029-5.052 6.029 0v8.399h4.988v-10.131c0-7.88-8.922-7.593-11.018-3.714v-2.155z"
                />
              </svg>
            </a>
            <a href="#!" class="text-[#FBFBFD]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-4 w-4"
                fill="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"
                />
              </svg>
            </a>
          </div>
        </div>

        <div class="mx-6 py-10 text-center md:text-left">
          <div class="grid-1 grid gap-8 md:grid-cols-2 lg:grid-cols-4">
            <div class="">
              <h6
                class="mb-4 flex items-center text-[#FDA400] justify-center font-semibold uppercase md:justify-start"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  fill="currentColor"
                  class="mr-3 h-4 w-4"
                >
                  <path
                    d="M12.378 1.602a.75.75 0 00-.756 0L3 6.632l9 5.25 9-5.25-8.622-5.03zM21.75 7.93l-9 5.25v9l8.628-5.032a.75.75 0 00.372-.648V7.93zM11.25 22.18v-9l-9-5.25v8.57a.75.75 0 00.372.648l8.628 5.033z"
                  />
                </svg>
                SwiftBid
              </h6>
              <p>
                Explore our platform to make bidding easy and efficient.
                Discover a wide range of items and participate in transparent
                and fair bidding processes.
              </p>
            </div>
            <!-- Products section -->
            <div class="">
              <h6
                class="mb-4 flex justify-center text-[#FDA400] font-semibold uppercase md:justify-start"
              >
                Made with
              </h6>
              <p class="mb-4">
                <span class="text-[#FBFBFD]">JSP</span>
              </p>
              <p class="mb-4">
                <span class="text-[#FBFBFD]">Sevlet</span>
              </p>
              <p class="mb-4">
                <span class="text-[#FBFBFD]">Maven</span>
              </p>
              <p class="mb-4">
                <span class="text-[#FBFBFD]">Mysql</span>
              </p>
            </div>
            <!-- Useful links section -->
            <div class="">
              <h6
                class="mb-4 flex text-[#FDA400] justify-center font-semibold uppercase md:justify-start"
              >
                Useful links
              </h6>
              <p class="mb-4">
                <a href="#!" class="text-[#FBFBFD]">Pricing</a>
              </p>
              <p class="mb-4">
                <a href="#!" class="text-[#FBFBFD]">Settings</a>
              </p>
              <p class="mb-4">
                <a href="#!" class="text-[#FBFBFD]">Orders</a>
              </p>
              <p>
                <a href="#!" class="text-[#FBFBFD]">Help</a>
              </p>
            </div>
            <!-- Contact section -->
            <div>
              <h6
                class="mb-4 flex justify-center text-[#FDA400] font-semibold uppercase md:justify-start"
              >
                Contact
              </h6>
              <p class="mb-4 flex items-center justify-center md:justify-start">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  fill="currentColor"
                  class="mr-3 h-5 w-5"
                >
                  <path
                    d="M11.47 3.84a.75.75 0 011.06 0l8.69 8.69a.75.75 0 101.06-1.06l-8.689-8.69a2.25 2.25 0 00-3.182 0l-8.69 8.69a.75.75 0 001.061 1.06l8.69-8.69z"
                  />
                  <path
                    d="M12 5.432l8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 01-.75-.75v-4.5a.75.75 0 00-.75-.75h-3a.75.75 0 00-.75.75V21a.75.75 0 01-.75.75H5.625a1.875 1.875 0 01-1.875-1.875v-6.198a2.29 2.29 0 00.091-.086L12 5.43z"
                  />
                </svg>
                Addis Ababa, Ethiopia
              </p>
              <p class="mb-4 flex items-center justify-center md:justify-start">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  fill="currentColor"
                  class="mr-3 h-5 w-5"
                >
                  <path
                    d="M1.5 8.67v8.58a3 3 0 003 3h15a3 3 0 003-3V8.67l-8.928 5.493a3 3 0 01-3.144 0L1.5 8.67z"
                  />
                  <path
                    d="M22.5 6.908V6.75a3 3 0 00-3-3h-15a3 3 0 00-3 3v.158l9.714 5.978a1.5 1.5 0 001.572 0L22.5 6.908z"
                  />
                </svg>
                info@swiftbid.com
              </p>
              <p class="mb-4 flex items-center justify-center md:justify-start">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 24 24"
                  fill="currentColor"
                  class="mr-3 h-5 w-5"
                >
                  <path
                    fill-rule="evenodd"
                    d="M1.5 4.5a3 3 0 013-3h1.372c.86 0 1.61.586 1.819 1.42l1.105 4.423a1.875 1.875 0 01-.694 1.955l-1.293.97c-.135.101-.164.249-.126.352a11.285 11.285 0 006.697 6.697c.103.038.25.009.352-.126l.97-1.293a1.875 1.875 0 011.955-.694l4.423 1.105c.834.209 1.42.959 1.42 1.82V19.5a3 3 0 01-3 3h-2.25C8.552 22.5 1.5 15.448 1.5 6.75V4.5z"
                    clip-rule="evenodd"
                  />
                </svg>
                + 01 234 567 88
              </p>
            </div>
          </div>
        </div>
      </div>

      <!--Copyright section-->
      <div class="bg-[#2d446c] p-6 text-center">
        <span>© 2023 Copyright:</span>
        <span class="font-semibold">SwiftBid</span>
      </div>
    </footer>
    <script>
      const menuBtn = document.getElementById("mobile-menu-button");
      const menu = document.getElementById("mobile-menu");

      menuBtn.addEventListener("click", () => {
        if (menu.classList.contains("hidden")) {
          menu.classList.remove("hidden");
        } else {
          menu.classList.add("hidden");
        }
      });
    </script>
  </body>
</html>
