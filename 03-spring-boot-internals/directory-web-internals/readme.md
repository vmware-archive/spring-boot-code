When @Audit annotation is used on a method, i.e. `findByEmail(..)`, we want perform logging and we want this behavior only when `DirectoryRepository` class is in the classpath.

## Create @Audit annotation

1. Add `@Audit` to the `findByEmail()` method
2. Create `@Audit` annotation

## Create Audit aspect

1. Create `SimpleAudit` aspect with `@Before` advice
2. Add logging code within the `@Before` advice
3. Create Join point expression such that when 
   @Audit annotation is used on a method, apply the aspect

## Use aspect on a condition

We want to create Audit aspect only when a particular class, i.e.`DirectoryRepository` class exists

1. Create `SimpleAudit` bean only when `DirectoryRepository` class 
   exist - use @ConditionalOnClass to check the presence of 
   `DirectoryRepository` class


