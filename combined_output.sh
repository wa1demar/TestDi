#!/bin/bash

output_file="combined_output.txt"
echo "" > "$output_file" # очищаємо перед використанням

find . -type f \( -name "*.kt" -o -name "*.kts" \) ! -name "$output_file" | while read file; do
  echo "// === FILE: $file ===" >> "$output_file"
  cat "$file" >> "$output_file"
  printf "\n\n" >> "$output_file"
done

echo "✅ Зібрано в output_file"