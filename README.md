# Approach

Step 1: Parse JSON and extract root values.

Step 2: Convert roots to decimal using BigInteger(value, base).

Step 3: Pick k roots.

Step 4: Multiply out (x - r) factors iteratively to form the polynomial.

Step 5: Output coefficients from constant term up to degree k.

Complexity Analysis

## Base Conversion:
Each root string length = L. Conversion = O(L) per root.
For n roots → O(n·L).

# Polynomial Expansion:
At step d, updating coefficients takes O(d).
For k roots → O(k²) coefficient updates.
Each update involves BigInteger ops:

Addition = O(M)

Multiplication = O(M²)

→ Total: O(k² · M²)

# Time Complexity (TC)
1. **Base Conversion:**  
   - Each root string has length `L`.  
   - Converting one root takes `O(L)`.  
   - For `n` roots → **O(n · L)**.  

2. **Polynomial Expansion:**  
   - At step `d`, updating coefficients costs `O(d)`.  
   - Across `k` roots → **O(k²)** coefficient updates.  
   - Each update involves `BigInteger` arithmetic:  
     - Addition → `O(M)`  
     - Multiplication → `O(M²)` (classical multiplication)  
   - Total → **O(k² · M²)**.  

👉 **Overall Time Complexity:**
O(n · L + k² · M²)

- `n` = number of roots  
- `L` = max digit length of a root (input size)  
- `k` = number of selected roots  
- `M` = max number of digits in polynomial coefficients
# Space Complexity:
Coefficient array of size k+1, each BigInteger up to size M.
→ O(k·M)
